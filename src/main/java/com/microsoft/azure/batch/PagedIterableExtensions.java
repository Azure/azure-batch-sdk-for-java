package com.microsoft.azure.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * Extensions methods for IPagedIterable to replicate behavior of Iterable
 * 
 * @param <T>
 */
public class PagedIterableExtensions<T> {
	/**
	 * Replicates for each behavior asynchronous
	 * @param iterable - An IPagedIterable object to iterate over
	 * @param func - A function to apply to each IPagedIterable item
	 * @return true on success and false if any exceptions were thrown during iteration
	 */
	public static <T, R> CompletableFuture<Boolean> forEachAsync(IPagedIterable<T> iterable, Function<T, R> func) {
		return CompletableFuture.supplyAsync(() -> _iterationTask(iterable, func));
	}

	private static <T, R> boolean _iterationTask(IPagedIterable<T> iterable, Function<T, R> func) {
		if (iterable == null || func == null) {
			throw new NullPointerException();
		}
		IPagedIterator<T> iterator = iterable.getPagedIterator();
		CompletableFuture<Boolean> hasNext;
		CompletableFuture<Boolean> res;
		try {
			do {
				hasNext = iterator.hasNextAsync();
				res = hasNext.thenCompose(hasNextSucess -> {
					if (hasNextSucess == true) {
						CompletableFuture<Boolean> moveNext = iterator.moveNextAsync();
						return moveNext.thenApply(moveSuccess -> {
							if (moveSuccess == true) {
								func.apply(iterator.current());
								return true;
							} else {
								return false;
							}
						});
					} else {
						return CompletableFuture.completedFuture(false);
					}
				});
			} while (res.get() == true);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Convert an iterable item to a list
	 * @param iterable An IPagedIterable item to be coverted to a list
	 * @return A list containing elements from the iterable
	 */
	public static <T> CompletableFuture<List<T>> toListAsync(IPagedIterable<T> iterable) {
		return CompletableFuture.supplyAsync(() -> _toListAsyncTask(iterable));
	}

	private static <T> List<T> _toListAsyncTask(IPagedIterable<T> iterable) {
		if (iterable == null) {
			throw new NullPointerException();
		}
		ConcurrentLinkedQueue<T> results = new ConcurrentLinkedQueue<T>();
		try {
			forEachAsync(iterable, item -> results.add(item)).get(); // blocks
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<T> list = new ArrayList<T>();
		list.addAll((Collection) results);
		return list;
	}
}
