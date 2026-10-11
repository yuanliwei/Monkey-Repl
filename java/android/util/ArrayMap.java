package android.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class ArrayMap<K, V> implements Map<K, V> {

    static final int[] EMPTY_IMMUTABLE_INTS = new int[0];
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    final boolean mIdentityHashCode;
    int[] mHashes;
    Object[] mArray;
    int mSize;

    int indexOf(Object key, int hash) {
        throw new RuntimeException("Stub!");
    }

    int indexOfNull() {
        throw new RuntimeException("Stub!");
    }

    public ArrayMap() {
        throw new RuntimeException("Stub!");
    }

    public ArrayMap(int capacity) {
        throw new RuntimeException("Stub!");
    }

    public ArrayMap(int capacity, boolean identityHashCode) {
        throw new RuntimeException("Stub!");
    }

    public ArrayMap(ArrayMap<K, V> map) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void clear() {
        throw new RuntimeException("Stub!");
    }

    public void erase() {
        if (mSize > 0) {
            throw new RuntimeException("Stub!");
        }
    }

    public void ensureCapacity(int minimumCapacity) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean containsKey(Object key) {
        throw new RuntimeException("Stub!");
    }

    public int indexOfKey(Object key) {
        throw new RuntimeException("Stub!");
    }

    int indexOfValue(Object value) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public V get(Object key) {
        throw new RuntimeException("Stub!");
    }

    public K keyAt(int index) {
        throw new RuntimeException("Stub!");
    }

    public V valueAt(int index) {
        throw new RuntimeException("Stub!");
    }

    public V setValueAt(int index, V value) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isEmpty() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public V put(K key, V value) {
        throw new RuntimeException("Stub!");
    }

    public void append(K key, V value) {
        throw new RuntimeException("Stub!");
    }

    public void validate() {
        throw new RuntimeException("Stub!");
    }

    public void putAll(ArrayMap<? extends K, ? extends V> array) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public V remove(Object key) {
        throw new RuntimeException("Stub!");
    }

    public V removeAt(int index) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int size() {
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
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new RuntimeException("Stub!");
    }

    public boolean removeAll(Collection<?> collection) {
        throw new RuntimeException("Stub!");
    }

    public boolean retainAll(Collection<?> collection) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Set<K> keySet() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("Stub!");
    }
}