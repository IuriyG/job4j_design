package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Iuriy Gaydarzhi.
 * @since 04.01.2021
 *
 * <b>3. Удалить head в односвязном списке.</b>
 * <b>Задание.</b>
 * Реализовать метод deleteFirst().
 * В методе delete должна быть проверка, что head != null.
 */

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /**
     * Метод добавляет значение в узел.
     *
     * @param value Значение.
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> last = current.next;
            current.next = prev;
            prev = current;
            current = last;
        }
        head = prev;
    }

    /**
     * Метод удаляет первое значение.
     *
     * @return Значение.
     */
    public T deleteFirst() {
        Node<T> node = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        return node.value;
    }

    /**
     * Метод удаляет последнее значение.
     *
     * @return Значение.
     */
    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node;
        if (head.next == null) {
            node = head;
            head = null;
            return node.value;
        }
        while (head.next != null) {
            head.next = null;
        }
        return head.value;
    }

    /**
     * Итератор.
     *
     * @return Новый итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            /**
             * Метод проверяет есть ли следующий элемент.
             * @return Значение.
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Метод возвращает первое значение.
             * @return Значение.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Модель для узла.
     *
     * @param <T> Значение.
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
