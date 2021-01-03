package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 02.01.2021
 */
public class SimpleList<E> implements Iterable<E> {
    private int size = 0;
    private Node<E> head;
    private Node<E> end;
    private int modCount;

    public void add(E value) {
        Node<E> newList = new Node<>();
        newList.item = value;
        if (end == null) {
            head = newList;
        } else {
            end.next = newList;
        }
        end = newList;
        this.size++;
        this.modCount++;
    }

    public E get(int index) {
        E resultData;
        checkIndex(index);
        resultData = getIndex(index).item;
        return resultData;
    }

    public Node<E> getIndex(int position) {
        Node<E> result;
        result = this.head;
        for (int i = 0; i < position; i++) {
            if (result.next != null) {
                result = result.next;
            }
        }
        return result;
    }

    public void checkIndex(int index) {
        Objects.checkIndex(index, this.size);
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = head.item;
                head = head.next;
                pointer++;
                return result;
            }
        };
    }

    private static class Node<E> {
        protected E item;
        protected Node<E> next;
    }
}
