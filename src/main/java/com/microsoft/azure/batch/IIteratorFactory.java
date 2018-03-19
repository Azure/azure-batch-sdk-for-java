package com.microsoft.azure.batch;

/**
 * Factory meant to be called for every call to "GetIterator" in com.microsoft.azure.batch.PagedIterable
 * 
 *
 * @param <T>
 */
public interface IIteratorFactory<T> {
	/**
	 * Returns the derived class that implements the specific protocols to traverse a collection
	 * @return An iterator that implements asynchronous protocols to traverse a collection
	 */
	IPagedIterator<T> newIteratorFactory();
}
