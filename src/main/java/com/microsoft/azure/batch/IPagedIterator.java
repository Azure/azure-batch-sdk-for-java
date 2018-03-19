package com.microsoft.azure.batch;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

/**
 * An iterator which exposes an asynchronous mechanism for iteration.
 * 
 *
 * @param <T> The type of the Iterator.
 */
public interface IPagedIterator<T> extends Iterator<T>{
	/**
	 * Gets the element in the collection at the current index of the iterator.
	 * @return The current element from the collection.
	 */
	T current();
	
	/**
	 * Begins an asynchronous call to advance check if there is another object in the collection
	 * 
	 * @return A java.util.concurrent.CompletableFuture object that represents the asynchronous operation.
	 */
	CompletableFuture<Boolean> hasNextAsync();
	
	/**
	 * Begins an asynchronous call to advance the iterator to the next element of the collection.
	 * @return A java.util.concurrent.CompletableFuture object that represents the asynchronous operation.
	 */
	CompletableFuture<Boolean> moveNextAsync();
	
	/**
	 * Begins an asynchronous call to set the iterator to its initial position, which is prior to the first element.
	 * 
	 * @return A java.util.concurrent.CompletableFuture object that represents the asynchronous operation.
	 */
	CompletableFuture<Boolean> resetAsync();
	
}
