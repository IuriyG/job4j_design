package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.02.2021
 */
public class SimpleHashMap<K, V> implements Iterable<Object> {
    private Object[] hashTable;
    private int size;

    public SimpleHashMap(int capacity) {
        this.hashTable = new Object[capacity];
    }

    public int hash(K key) {
        return key.hashCode() % hashTable.length;
    }

    public int getSize() {
        return size;
    }

    public void insert(K key, V value) {
        if (size == hashTable.length) {
            newHashTable();
        }
        if (!checkIndex(key)) {
            hashTable[hash(key)] = value;
            size++;
        }
    }

    public void newHashTable() {
        hashTable = Arrays.copyOf(hashTable, hashTable.length * 2);
        size *= 2;
    }

    public Object get(K key) {
        return hashTable[hash(key)];
    }

    public boolean delete(K key) {
        if (checkIndex(key)) {
            hashTable[hash(key)] = null;
            return true;
        }
        return false;
    }

    public boolean checkIndex(K key) {
        return hashTable[hash(key)] != null;
    }

    @Override
    public Iterator<Object> iterator() {

        return new Iterator<>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < hashTable.length;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return hashTable[pointer++];
            }
        };
    }
}
