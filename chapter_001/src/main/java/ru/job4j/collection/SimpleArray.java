package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 26.12.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    private final int index = 0;
    private final Object[] container;
    private int modCount;

    public SimpleArray(int capacity) {
        this.container = new Object[capacity];
    }

    public T get(int index) {
        checkIndex(index);
        modCount++;
        return (T) this.container[index];
    }

    public void add(T model) {
        modCount++;

        this.container[index] = model;
    }

    public void checkIndex(int index) {
        Objects.checkIndex(index, this.index);
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (pointer <= index) {
                    for (int i = pointer; i < container.length; i++) {
                        if (container[i] != null) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[pointer++];
            }
        };
    }
}
