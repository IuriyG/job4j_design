package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] data;
    private final int index = 0;

    public SimpleArray(Object[] model) {
        this.data = model;
    }


    public void set(int index, T model) {
        check(index);
        this.data[index] = model;
    }

    public void add(T model) {
        this.data[index] = model;
    }

    public void remove(int index) {
        check(index);
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
    }

    public Object get(int index) {
        check(index);
        return this.data[index];
    }

    public void check(int index) {
        Objects.checkIndex(index, data.length);
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                + "data=" + Arrays.toString(data)
                + ", index=" + index
                + '}';
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + index;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < data.length;
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
