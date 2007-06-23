package org.protege.service.owlapi.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class LazySet<X> implements Set<X> {
	
	private Set<X> store;
	

	protected abstract Set<X> build();
	
	private void prepareForRead() {
		if (store == null) {
			store = build();
		}
	}
	
	public boolean add(X o) {
		if (store != null) {
			return store.add(o);
		}
		return true;
	}

	public boolean addAll(Collection<? extends X> c) {
		if (store != null) {
			return store.addAll(c);
		}
		return true;
	}

	public void clear() {
		if (store != null) {
			store.clear();
		}
	}

	public boolean contains(Object o) {
		prepareForRead();
		return store.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		prepareForRead();
		return store.containsAll(c);
	}

	public boolean isEmpty() {
		prepareForRead();
		return store.isEmpty();
	}

	public Iterator<X> iterator() {
		prepareForRead();
		return store.iterator();
	}

	public boolean remove(Object o) {
		prepareForRead();
		return store.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		prepareForRead();
		return store.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		prepareForRead();
		return store.retainAll(c);
	}

	public int size() {
		prepareForRead();
		return store.size();
	}

	public Object[] toArray() {
		prepareForRead();
		return store.toArray();
	}

	public <T> T[] toArray(T[] a) {
		prepareForRead();
		return store.toArray(a);
	}

}
