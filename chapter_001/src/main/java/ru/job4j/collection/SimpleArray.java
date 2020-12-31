package ru.job4j.collection;

import java.util.*;

/**
 * @author Iuriy Gaydarzhi.
 * @since 26.12.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int index = 0;
    private int modCount;

    public SimpleArray(int capacity) {
        this.container = new Object[capacity];
    }

    public T get(int index) {
        checkIndex(index);
        return (T) this.container[index];
    }

    public void add(T model) {
        modCount++;
        if (index == container.length) {
            newContainer();
        }
        this.container[index++] = model;
    }

    private void newContainer() {
        container = Arrays.copyOf(container, container.length * 2);
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
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < index;
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
