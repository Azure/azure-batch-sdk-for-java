package com.microsoft.azure.batch;

import java.util.Iterator;

class PagedIterable<T> implements IPagedIterable<T>{
	private IIteratorFactory<T> _iteratorFactory;
	PagedIterable (IIteratorFactory<T> iteratorFactory){
		_iteratorFactory = iteratorFactory;
	}
	
	 	
	//Iterable
	@Override
	public Iterator<T> iterator() {
		IPagedIterator<T> _newIterator = _iteratorFactory.newIteratorFactory();
		return _newIterator;
	}

	//IPagedIterable
	@Override
	public IPagedIterator<T> getPagedIterator() {
		IPagedIterator<T> _newIterator = _iteratorFactory.newIteratorFactory();
		return _newIterator;
	}

	
}
