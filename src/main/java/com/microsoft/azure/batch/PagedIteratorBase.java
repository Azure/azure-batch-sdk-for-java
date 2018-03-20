/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

abstract class PagedIteratorBase<T> implements IPagedIterator<T> {
	protected Integer _currentIndex;
	private final PagedIteratorBase<T> _implInstance;
	protected List<Object> _currentBatch;
	protected SkipTokenHandler _skipHandler;
	private final ExecutorService executor = Executors.newCachedThreadPool();

	PagedIteratorBase() {
		_implInstance = this;
		_implInstance.reset();
	}

	// IPagedIterator
	@Override
	public abstract T current();

	protected abstract Future<Boolean> getNextBatchFromServerAsync(SkipTokenHandler skipHandler);

	// Iterator
	@Override
	public boolean hasNext() {
		Future<Boolean> asyncTask = hasNextAsync();

		try {
			return asyncTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}

	// IPagedIterator
	@Override
	public Future<Boolean> hasNextAsync() {
		Future<Boolean> futureTask = (Future<Boolean>) executor.submit(new _hasNextTask<T>(_implInstance));
		return futureTask;
	}

	// Iterator
	@Override
	public T next() throws NoSuchElementException {
		Future<Boolean> asyncTask = moveNextAsync();

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
	public Future<Boolean> moveNextAsync() {
		Future<Boolean> futureTask = executor.submit(new _moveNextTask<T>(_implInstance));
		return futureTask;
	}



	/**
	 * Synchronously reset the iterator. Initial state places the iterator prior to
	 * the the first item in the collection and refreshes the batch
	 */
	public void reset() {
		Future<Boolean> asyncTask = resetAsync();
		try {
			asyncTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	// IPagedIterator
	@Override
	public Future<Boolean> resetAsync() {
		Future<Boolean> futureTask = executor.submit(new _resetTask<T>(_implInstance));
		return futureTask;
	}

}

/**
 * Task to asynchronously check if there is any more data to iterate over
 * @param <T>
 * 
 * @return true if there is otherwise false
 */
class _hasNextTask<T> implements Callable<Boolean> {
	private PagedIteratorBase<T> _iterator;
	public _hasNextTask(PagedIteratorBase<T> iterator) {
		_iterator = iterator;
	}
	@Override
	public Boolean call() throws Exception {
		if ((_iterator._currentBatch.size() != 0) && _iterator._currentIndex + 1 < _iterator._currentBatch.size()) {
			return true;
		}
		if (_iterator._skipHandler.getAtLeastOneCallMade() && !_iterator._skipHandler.isMoreData()) {
			return false;
		}
		return true;
	}
}

/**
 * Task to asynchronously move the iterator to the next item in the collection
 * @param <T>
 * 
 * @return true on iterator successfully moving, false if out of data
 * @throws ExecutionException 
 * @throws InterruptedException 
 */
class _moveNextTask<T> implements Callable<Boolean> {
	private PagedIteratorBase<T> _iterator;
	public _moveNextTask(PagedIteratorBase<T> iterator) {
		_iterator = iterator;
	}
	
	@Override
	public Boolean call() throws Exception {
		// move iterator
		_iterator._currentIndex++;

		// If we have enough data return true
		if ((_iterator._currentBatch.size() != 0) && (_iterator._currentIndex < _iterator._currentBatch.size())) {
			return true;
		}

		if (_iterator._skipHandler.getAtLeastOneCallMade() && !_iterator._skipHandler.isMoreData()) {
			return false;
		}

		Future<Boolean> asyncGetData = _iterator.getNextBatchFromServerAsync(_iterator._skipHandler);
		try {
			asyncGetData.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		} 

		if ((_iterator._currentBatch.size() != 0)) {
			_iterator._currentIndex = 0;
			return true;
		}
		
		return false;
	}

	
}

/**
 * Task to asynchronously call to reset iterator back to initial state. Initial
 * state places the iterator prior to the the first item in the collection and
 * refreshes the batch
 * @param <T>
 * 
 * @return true on completion
 */
class _resetTask<T> implements Callable<Boolean> {
	private PagedIteratorBase<T> _iterator;
	public _resetTask(PagedIteratorBase<T> iterator) {
		_iterator = iterator;
	}
	
	@Override
	public Boolean call() throws Exception {	
		_iterator._skipHandler = new SkipTokenHandler();
		_iterator._currentIndex = -1;
		_iterator._currentBatch = new ArrayList<Object>();
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