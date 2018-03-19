package com.microsoft.azure.batch;

/**
 * Exposes iterators for a paged collection. These iterators support simple iteration over a paged collection of a 
 * specified type.
 * 
 *
 * @param <T> The type of the iterator.
 */
public interface IPagedIterable<T> extends Iterable<T> {
	/**
	 * Returns an asynchronous iterator that iterates through the paged collection.
	 * @return
	 */
	IPagedIterator<T> getPagedIterator();
}
