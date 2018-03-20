/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.batch;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PagedCollectionUnitTests {
	
	
	@Test(expected = NullPointerException.class)
	public void ifIterableIsNull_ThenForEachAsyncThrowsArgumentNullException() throws Throwable {
		IPagedIterable<Character> iterable = null;
		Future<Boolean> future = PagedIterableExtensions.forEachAsync(iterable, new sleepOneSecond<Character>());
		try {
			future.get();
		} catch (InterruptedException e) {
			throw e;
		} catch (ExecutionException e) {
			throw e.getCause();
		}
	}

	@Test(expected = NullPointerException.class)
	public void ifFuncIsNull_ThenForEachAsyncThrowsArgumentNullException() throws Throwable {
		IPagedIterable<Character> iterable = new PagedAlphabet();
		Function<Character, Object> func = null;
		Future<Boolean> future = PagedIterableExtensions.forEachAsync(iterable, func);
		try {
			future.get();
		} catch (InterruptedException e) {
			throw e;
		} catch (ExecutionException e) {
			throw e.getCause();
		}
	}

	@Test
	public void forEachAsyncVisitsEveryMember() throws InterruptedException, ExecutionException {
		List<Character> visited = new ArrayList<Character>();
		IPagedIterable<Character> iterable = new PagedAlphabet();
		Future<Boolean> futureTask = PagedIterableExtensions.forEachAsync(iterable, new addToList<Character>(visited));
		futureTask.get();
		assertEquals(26, visited.size());
		assertEquals('A', visited.get(0).charValue());
		assertEquals('Z', visited.get(visited.size() - 1).charValue());
	}

	@Test(expected = NullPointerException.class)
	public void ifIterableIsNull_ThenToListAsyncThrowsArgumentException() throws Throwable {
		IPagedIterable<Character> iterable = null;
		Future<List<Character>> future = PagedIterableExtensions.toListAsync(iterable);
		try {
			future.get();
		} catch (InterruptedException e) {
			throw e;
		} catch (ExecutionException e) {
			throw e.getCause();
		}
	}
	
	@Test
	public void toListCollectsAllElementsOfSequence() throws InterruptedException, ExecutionException {
		IPagedIterable<Character> iterable = new PagedAlphabet(4,10);
		List<Character> list = PagedIterableExtensions.toListAsync(iterable).get();
		assertEquals(10, list.size());
		assertEquals('A', list.get(0).charValue());
		assertEquals('J', list.get(list.size()-1).charValue());
	}
	
	@Test
	public void toListCorrectlyHandlesEmptyCollections() throws InterruptedException, ExecutionException {
		IPagedIterable<Character> iterable = new PagedAlphabet(4,0);
		List<Character> list = PagedIterableExtensions.toListAsync(iterable).get();
        assertEquals(0, list.size());
	}
	
	@Test(expected = CancellationException.class)
	public void toListSupportsCancellation() throws InterruptedException, ExecutionException {
		IPagedIterable<Character> iterable = new PagedAlphabet(4,10);
		Future<List<Character>> list = PagedIterableExtensions.toListAsync(iterable);	
		list.cancel(true);
		list.get();
	}
	
	@Test(expected = CancellationException.class)
	public void forEachAsyncSupportsCancellation() throws InterruptedException, ExecutionException {
		IPagedIterable<Character> iterable = new PagedAlphabet(4,10);
		Future<Boolean> future = PagedIterableExtensions.forEachAsync(iterable, new sleepOneSecond<Character>());
		future.cancel(true);
		future.get();
	}
}

class sleepOneSecond<T> implements Function<T,Boolean>{
	public Boolean apply(T arg0) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
}

class addToList<T> implements Function<T, Boolean> {
	private List<T> _list;
	public addToList(List<T> list) {
		_list = list;
	}
	@Override
	public Boolean apply(T item) {
		_list.add(item);
		return true;
	}
}

class AlphabetFactory implements IIteratorFactory<Character> {
	final int _pageSize;
	final int _stopAfter;

	public AlphabetFactory(int pageSize, int stopAfter) {
		_pageSize = pageSize;
		_stopAfter = stopAfter;
	}

	@Override
	public IPagedIterator<Character> newIteratorFactory() {
		IPagedIterator<Character> iterator = new PagedAlphabetEnumerator(_pageSize, _stopAfter);
		return iterator;
	}
}

class PagedAlphabet extends PagedIterable<Character> {
	public PagedAlphabet() {
		this(3);
	}

	public PagedAlphabet(int pageSize) {
		this(pageSize, 26);
	}

	public PagedAlphabet(int pageSize, int stopAfter) {
		super(new AlphabetFactory(pageSize, stopAfter));
	}
}

class PagedAlphabetEnumerator extends PagedIteratorBase<Character> {
	private static final ExecutorService executor = Executors.newCachedThreadPool();
	private final int _pageSize;
	private final int _last;

	public PagedAlphabetEnumerator(int pageSize, int stopAfter) {
		_pageSize = pageSize;
		_last = (int) 'A' + stopAfter - 1;
	}

	@Override
	public Character current() {
		return (Character) (_currentBatch.get(_currentIndex));
	}

	@Override
	protected Future<Boolean> getNextBatchFromServerAsync(SkipTokenHandler skipHandler) {
		Future<Boolean> futureTask = executor.submit(new _getNextBatchFromServerAsync(skipHandler, _pageSize, _last, _currentBatch));
		return futureTask;
	}
}

class _getNextBatchFromServerAsync implements Callable<Boolean>{
	private SkipTokenHandler _skipHandler;
	private int _pageSize;
	private int _last;
	private List<Object> _currentBatch;
	public _getNextBatchFromServerAsync(SkipTokenHandler skipHandler, int pageSize, int last, List<Object> currentBatch) {
		_skipHandler = skipHandler;
		_pageSize = pageSize;
		_last = last;
		_currentBatch = currentBatch;
	}

	@Override
	public Boolean call() throws Exception {
		ArrayList<Character> chars = new ArrayList<Character>();
		if (!_skipHandler.getAtLeastOneCallMade()) {
			for (int i = 0; i < _pageSize && (('A' + i) <= _last); i++) {
				chars.add((char) ('A' + i));
			}
		} else {
			char currChar = _skipHandler.getSkipToken().charAt(0);
			for (int i = 0; i < _pageSize && (((currChar + 1) + i) <= _last); i++) {
				chars.add((char) ((currChar + 1) + i));
			}
		}
		_currentBatch.clear();
		Collections.addAll(_currentBatch, chars.toArray());
		_skipHandler.setAtLeastOneCallMade(true);
		if (chars.size() > 0 && chars.get(chars.size() - 1) < _last) {
			_skipHandler.setSkipToken("" + chars.get(chars.size() - 1));
		} else {
			_skipHandler.setSkipToken(null);
		}

		return true;
	}
}