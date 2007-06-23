package org.protege.service.owlapi.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;

public class ConcurrentSet<X> implements Set<X> {
	private Set<X> store = new HashSet<X>();
	private boolean cloned = false;
	private WeakHashMap<Iterator<X>,Object> iterators = new WeakHashMap<Iterator<X>, Object>();
	private ReadWriteLock lock;
	
	public ConcurrentSet(ReadWriteLock lock) {
		this.lock = lock;
	}
	
	private void prepareForWrite() {
		if (needsCopyForWrite()) {
			Set<X> oldStore = store;
			store = new HashSet<X>();
			store.addAll(oldStore);
		}
	}
	
	private boolean needsCopyForWrite() {
		return cloned || !iterators.isEmpty();
	}
	
	public Set<X> clone() {
		lock.writeLock().lock();
		try {
			cloned=true;
			return store;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public boolean add(X o) {
		lock.writeLock().lock();
		try {
			prepareForWrite();
			return store.add(o);
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public boolean addAll(Collection<? extends X> c) {
		lock.writeLock().lock();
		try {
			prepareForWrite();
			return store.addAll(c);
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public void clear() {
		lock.writeLock().lock();
		try {
			prepareForWrite();
			store.clear();
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public boolean contains(Object o) {
		lock.readLock().lock();
		try {
			return store.contains(o);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	public boolean containsAll(Collection<?> c) {
		lock.readLock().lock();
		try {
			return store.containsAll(c);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	public boolean isEmpty() {
		lock.readLock().lock();
		try {
			return store.isEmpty();
		}
		finally {
			lock.readLock().unlock();
		}
	}

	public Iterator<X> iterator() {
		Iterator<X> iterator;
		lock.readLock().lock();
		try {
			 iterator = new Iterator<X>() {
				private Iterator<X> it = store.iterator();

				public boolean hasNext() {
					lock.readLock().lock();
					try {
						return it.hasNext();
					}
					finally {
						lock.readLock().unlock();
					}
				}

				public X next() {
					X next;
					boolean endOfLine;
					lock.readLock().lock();
					try {
						next = it.next();
						endOfLine = !it.hasNext();
					}
					finally {
						lock.readLock().unlock();
					}
					if (endOfLine) {
						lock.writeLock().lock();
						try {
							iterators.remove(this);
						}
						finally {
							lock.writeLock().unlock();
						}
					}
					return next;
				}

				public void remove() {
					throw new UnsupportedOperationException("This is a read-only iterator");
				}

			};
		}
		finally {
			lock.readLock().unlock();
		}
		lock.writeLock().lock();
		try {
			iterators.put(iterator, null);
		}
		finally {
			lock.writeLock().unlock();
		}
		return iterator;
	}

	public boolean remove(Object o) {
		lock.writeLock().lock();
		try {
			return store.remove(o);
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public boolean removeAll(Collection<?> c) {
		lock.writeLock().lock();
		try {
			return store.removeAll(c);
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public boolean retainAll(Collection<?> c) {
		lock.writeLock().lock();
		try {
			return store.retainAll(c);
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public int size() {
		lock.readLock().lock();
		try {
			return store.size();
		}
		finally {
			lock.readLock().unlock();
		}
	}

	public Object[] toArray() {
		lock.readLock().lock();
		try {
			return store.toArray();
		}
		finally {
			lock.readLock().unlock();
		}
	}

	public <T> T[] toArray(T[] a) {
		lock.readLock().lock();
		try {
			return store.toArray(a);
		}
		finally {
			lock.readLock().unlock();
		}
	}

}
