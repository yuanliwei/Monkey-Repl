package android.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class ArraySet<E> implements Set<E> {
    static Object[] sBaseCache;
    static int sBaseCacheSize;
    static Object[] sTwiceBaseCache;
    static int sTwiceBaseCacheSize;
    final boolean mIdentityHashCode;
    int[] mHashes;
    Object[] mArray;
    int mSize;

    public ArraySet() {
        throw new RuntimeException("Stub!");
    }

    public ArraySet(int capacity) {
        throw new RuntimeException("Stub!");
    }

    public ArraySet(int capacity, boolean identityHashCode) {
        throw new RuntimeException("Stub!");
    }

    public ArraySet(ArraySet<E> set) {
        throw new RuntimeException("Stub!");
    }

    public ArraySet(Collection<E> set) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void clear() {
        throw new RuntimeException("Stub!");
    }

    public void ensureCapacity(int minimumCapacity) {
        if (mHashes.length < minimumCapacity) {
            throw new RuntimeException("Stub!");
        }
    }

    @Override
    public boolean contains(Object key) {
        throw new RuntimeException("Stub!");
    }

    public int indexOf(Object key) {
        throw new RuntimeException("Stub!");
    }

    public E valueAt(int index) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isEmpty() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean add(E value) {
        throw new RuntimeException("Stub!");
    }

    public void append(E value) {
        throw new RuntimeException("Stub!");
    }

    public void addAll(ArraySet<? extends E> array) {
        final int N = array.mSize;
        ensureCapacity(mSize + N);
        if (mSize == 0) {
            throw new RuntimeException("Stub!");
        } else {

        }
    }

    @Override
    public boolean remove(Object object) {
        throw new RuntimeException("Stub!");
    }

    public E removeAt(int index) {
        throw new RuntimeException("Stub!");
    }

    public boolean removeAll(ArraySet<? extends E> array) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int size() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Object[] toArray() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public <T> T[] toArray(T[] array) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object object) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Iterator<E> iterator() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new RuntimeException("Stub!");
    }
}