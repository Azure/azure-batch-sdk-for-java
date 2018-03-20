/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.batch;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class PagedCollectionUnitTests {

	@Test(expected = NullPointerException.class)
	public void ifIterableIsNull_ThenForEachAsyncThrowsArgumentNullException() throws Throwable {
		IPagedIterable<Character> iterable = null;
		CompletableFuture<Boolean> future = PagedIterableExtensions.forEachAsync(iterable, s -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		});
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
		CompletableFuture<Boolean> future = PagedIterableExtensions.forEachAsync(iterable, func);
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
		PagedIterableExtensions.forEachAsync(iterable, s -> {
			visited.add(s);
			return true;
		}).get();
		assertEquals(26, visited.size());
		assertEquals('A', visited.get(0).charValue());
		assertEquals('Z', visited.get(visited.size() - 1).charValue());
	}

	@Test(expected = NullPointerException.class)
	public void ifIterableIsNull_ThenToListAsyncThrowsArgumentException() throws Throwable {
		IPagedIterable<Character> iterable = null;
		CompletableFuture<List<Character>> future = PagedIterableExtensions.toListAsync(iterable);
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
		CompletableFuture<List<Character>> list = PagedIterableExtensions.toListAsync(iterable);	
		list.cancel(true);
		list.get();
	}
	
	@Test(expected = CancellationException.class)
	public void forEachAsyncSupportsCancellation() throws InterruptedException, ExecutionException {
		IPagedIterable<Character> iterable = new PagedAlphabet(4,10);
		CompletableFuture<Boolean> future = PagedIterableExtensions.forEachAsync(iterable, s -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		});	
		future.cancel(true);
		future.get();
	}

}

class AlphabetFactory<T> implements IIteratorFactory<T> {
	final int _pageSize;
	final int _stopAfter;

	public AlphabetFactory(int pageSize, int stopAfter) {
		_pageSize = pageSize;
		_stopAfter = stopAfter;
	}

	@Override
	public IPagedIterator<T> newIteratorFactory() {
		return (IPagedIterator<T>) new PagedAlphabetEnumerator(_pageSize, _stopAfter);
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
		super(new AlphabetFactory<Character>(pageSize, stopAfter));
	}
}

class PagedAlphabetEnumerator extends PagedIteratorBase<Character> {
	final int _pageSize;
	final int _last;

	public PagedAlphabetEnumerator(int pageSize, int stopAfter) {
		_pageSize = pageSize;
		_last = (int) 'A' + stopAfter - 1;
	}

	@Override
	public Character current() {
		return (Character) (_currentBatch[_currentIndex]);
	}

	@Override
	protected CompletableFuture getNextBatchFromServerAsync(SkipTokenHandler skipHandler) {
		return CompletableFuture.supplyAsync(() -> _getNextBatchFromServerAsync(skipHandler));
	}

	Boolean _getNextBatchFromServerAsync(SkipTokenHandler skipHandler) {
		ArrayList<Character> chars = new ArrayList<Character>();
		if (!skipHandler.getAtLeastOneCallMade()) {
			for (int i = 0; i < _pageSize && (('A' + i) <= _last); i++) {
				chars.add((char) ('A' + i));
			}
		} else {
			char currChar = skipHandler.getSkipToken().charAt(0);
			for (int i = 0; i < _pageSize && (((currChar + 1) + i) <= _last); i++) {
				chars.add((char) ((currChar + 1) + i));
			}
		}

		_currentBatch = chars.toArray();
		skipHandler.setAtLeastOneCallMade(true);
		if (chars.size() > 0 && chars.get(chars.size() - 1) < _last) {
			skipHandler.setSkipToken("" + chars.get(chars.size() - 1));
		} else {
			skipHandler.setSkipToken(null);
		}

		return true;
	}

}
