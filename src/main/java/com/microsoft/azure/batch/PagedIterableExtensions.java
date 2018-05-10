/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Extensions methods for IPagedIterable to replicate behavior of Iterable
 * 
 * @param <T>
 */
public class PagedIterableExtensions<T> {
	private static final ExecutorService executor = Executors.newCachedThreadPool();
	/**
	 * Replicates for each behavior asynchronous
	 * 
	 * @param iterable
	 *            - An IPagedIterable object to iterate over
	 * @param func
	 *            - A function to apply to each IPagedIterable item
	 * @return true on success and false if any exceptions were thrown during
	 *         iteration
	 */
	public static <T, R> Future<Boolean> forEachAsync(IPagedIterable<T> iterable, Function<T, R> func) {
		Future<Boolean> futureTask = executor.submit(new _iterationTask<T, R>(iterable, func));
		return futureTask;
	}

	/**
	 * Convert an iterable item to a list
	 * 
	 * @param iterable
	 *            An IPagedIterable item to be coverted to a list
	 * @return A list containing elements from the iterable
	 */
	public static <T> Future<List<T>> toListAsync(IPagedIterable<T> iterable) {
		Future<List<T>> futureTask = executor.submit(new _toListAsyncTask<T>(iterable));
		return futureTask;
	}

}

class _iterationTask<T, R> implements Callable<Boolean> {
	private IPagedIterable<T> _iterable;
	private Function<T, R> _func;

	public _iterationTask(IPagedIterable<T> iterable, Function<T, R> func) {
		_iterable = iterable;
		_func = func;
	}

	@Override
	public Boolean call() throws Exception {
		if (_iterable == null || _func == null) {
			throw new NullPointerException();
		}
		IPagedIterator<T> iterator = _iterable.getPagedIterator();
		Future<Boolean> res;
		boolean hasNext = false;
		boolean moveSuccess = false;
		try {
			do {
				res = iterator.hasNextAsync();
				hasNext = res.get();
				if (hasNext) {
					res = iterator.moveNextAsync();
					moveSuccess = res.get();
					if (moveSuccess == true) {
						_func.apply(iterator.current());
					} else {
						return false;
					}
				} 
			} while (hasNext && moveSuccess);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

class  _toListAsyncTask<T> implements Callable<List<T>> {
	private IPagedIterable<T> _iterable;
	public _toListAsyncTask(IPagedIterable<T> iterable) {
		_iterable = iterable;
	}
	
	@Override
	public List<T> call() throws Exception {
		if (_iterable == null) {
			throw new NullPointerException();
		}
		ConcurrentLinkedQueue<T> results = new ConcurrentLinkedQueue<T>();
		try {
			PagedIterableExtensions.forEachAsync(_iterable, new _addToList<T>(results)).get(); // blocks
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<T> list = new ArrayList<T>();
		list.addAll((Collection<T>) results);
		return list;
	}
}

class _addToList<T> implements Function<T, Boolean> {
	private ConcurrentLinkedQueue<T> _list;
	public _addToList(ConcurrentLinkedQueue<T> list) {
		_list = list;
	}
	@Override
	public Boolean apply(T item) {
		_list.add(item);
		return true;
	}
}
