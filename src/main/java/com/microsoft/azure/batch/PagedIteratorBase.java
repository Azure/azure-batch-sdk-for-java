/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.batch;

import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

abstract class PagedIteratorBase<T> implements IPagedIterator<T> {
	protected int _currentIndex;
	private final PagedIteratorBase<T> _implInstance;
	protected Object[] _currentBatch;
	private SkipTokenHandler _skipHandler;

	PagedIteratorBase() {
		_implInstance = this;
		_implInstance.reset();
	}

	// IPagedIterator
	@Override
	public abstract T current();

	protected abstract CompletableFuture<T> getNextBatchFromServerAsync(SkipTokenHandler skipHandler);

	// Iterator
	@Override
	public boolean hasNext() {
		CompletableFuture<Boolean> asyncTask = hasNextAsync();

		try {
			return asyncTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}

	// IPagedIterator
	@Override
	public CompletableFuture<Boolean> hasNextAsync() {
		return CompletableFuture.supplyAsync(this::_hasNextTask);
	}

	/**
	 * Task to asynchronously check if there is any more data to iterate over
	 * 
	 * @return true if there is otherwise false
	 */
	private boolean _hasNextTask() {
		if ((_currentBatch != null) && _currentIndex + 1 < _currentBatch.length) {
			return true;
		}
		if (_skipHandler.getAtLeastOneCallMade() && !_skipHandler.isMoreData()) {
			return false;
		}
		return true;

	}

	// Iterator
	@Override
	public T next() throws NoSuchElementException {
		CompletableFuture<Boolean> asyncTask = moveNextAsync();

		try {
			if (asyncTask.get()) {
				return current();
			} else {
				return null;
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}

	// IPagedIterator
	@Override
	public CompletableFuture<Boolean> moveNextAsync() {
		return CompletableFuture.supplyAsync(this::_moveNextTask);
	}

	/**
	 * Task to asynchronously move the iterator to the next item in the collection
	 * 
	 * @return true on iterator successfully moving, false if out of data
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private boolean _moveNextTask() {
		// move iterator
		_currentIndex++;

		// If we have enough data return true
		if ((_currentBatch != null) && (_currentIndex < _currentBatch.length)) {
			return true;
		}

		if (_skipHandler.getAtLeastOneCallMade() && !_skipHandler.isMoreData()) {
			return false;
		}

		CompletableFuture<T> asyncGetData = getNextBatchFromServerAsync(_skipHandler);
		try {
			asyncGetData.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		} 

		if ((_currentBatch != null) && (_currentBatch.length > 0)) {
			_currentIndex = 0;
			return true;
		}

		return false;
	}

	/**
	 * Synchronously reset the iterator. Initial state places the iterator prior to
	 * the the first item in the collection and refreshes the batch
	 */
	public void reset() {
		CompletableFuture<Boolean> asyncTask = resetAsync();
		try {
			asyncTask.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Better Exception Handling
			e.printStackTrace();
		}

	}

	// IPagedIterator
	@Override
	public CompletableFuture<Boolean> resetAsync() {
		return CompletableFuture.supplyAsync(this::_resetTask);
	}

	/**
	 * Task to asynchronously call to reset iterator back to initial state. Initial
	 * state places the iterator prior to the the first item in the collection and
	 * refreshes the batch
	 * 
	 * @return true on completion
	 */
	private boolean _resetTask() {
		_skipHandler = new SkipTokenHandler();
		_currentIndex = -1;
		_currentBatch = null;
		return true;
	}

}

/**
 * Class to assist in knowledge of data continuation
 * 
 *
 */
class SkipTokenHandler {

	private String _skipToken; // indicator of current stage data remaining
	private boolean _hasBeenCalled = false;

	public boolean getAtLeastOneCallMade() {
		return _hasBeenCalled;
	}

	public void setAtLeastOneCallMade(boolean value) {
		_hasBeenCalled = value;
	}

	public String getSkipToken() {
		return _skipToken;
	}

	public void setSkipToken(String value) {
		_skipToken = value;
		_hasBeenCalled = true;
	}

	public boolean isMoreData() {
		return !((_skipToken == null) || (_skipToken.equals("")));
	}
}