package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] data;
    private int index = 0;

    public SimpleArray(int capacity) {
        this.data = new Object[capacity];
    }

    public void set(int index, T model) {
        check(index);
        this.data[index] = model;
    }

    public void add(T model) {
        this.data[index++] = model;
    }

    public void remove(int index) {
        check(index);
        System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        data[data.length - this.index] = null;
    }

    public Object get(int index) {
        check(index);
        return this.data[index];
    }

    public void check(int index) {
        Objects.checkIndex(index, this.index);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[pointer++];
            }
        };
    }
}
