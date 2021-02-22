package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.02.2021
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    private final double loadFactor = 0.75;
    private Node<K, V>[] hashTable = new Node[8];
    private int size;
    private int modCount;

    private void resize() {
        if (this.size >= hashTable.length * loadFactor) {
            Node<K, V>[] tempHashTable = hashTable;
            hashTable = new Node[tempHashTable.length * 2];
            this.size = 0;
            this.modCount = 0;
            for (Node<K, V> node : tempHashTable) {
                if (node != null) {
                    insert(node.getKey(), node.getValue());
                }
            }
        }
    }

    public boolean insert(K key, V value) {
        resize();
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (hashTable[index] == null || Objects.equals(hashTable[index].key, key)) {
            hashTable[index] = newNode;
            this.size++;
            this.modCount++;
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (hashTable[index] != null && Objects.equals(hashTable[index].key, key)) {
            hashTable[index] = null;
            this.size--;
            this.modCount++;
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public int hash(K key) {
        return key == null ? 0 : key.hashCode() % hashTable.length;
    }

    public V get(K key) {
        Node<K, V> index = hashTable[hash(key)];
        if (index != null && (Objects.equals(index.key, key))) {
            return index.getValue();
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (hashTable[pointer] == null && pointer < hashTable.length - 1) {
                    pointer++;
                }
                return hashTable[pointer] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return hashTable[pointer++].key;
            }
        };
    }

    protected static class Node<K, V> {
        private final K key;
        private final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }
}
