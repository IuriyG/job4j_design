package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 02.01.2021
 *
 * Необходимо создать динамический контейнер с методами:
 * 1) add(E value); (добавляет в конец);
 * 2) E get(int index);
 * 3) реализовать интерфейс Iterable<E>.
 * Внутри контейнер должен базироваться на связанном списке(Node<E> node).
 * Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 * Итератор должен реализовывать fail-fast поведение.
 * В методах, где используется индекс нужно делать валидацию.
 */
public class SimpleList<E> implements Iterable<E> {
    private int size = 0;
    private Node<E> head;
    private Node<E> end;
    private int modCount;

    /**
     * Метод добавляет элемент в конец списка. Увеличивает размерность списка и счетчик изменений.
     *
     * @param value Элемент.
     */
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

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     *
     * @param index Индекс
     * @return Элемент.
     */
    public E get(int index) {
        checkIndex(index);
        Node<E> result = this.head;
        for (int i = 0; i < index; i++) {
            if (result.next != null) {
                result = result.next;
            }
        }
        return result.item;
    }

    /**
     * Метод проверяет находится ли значение index в границах добавленных элементов.
     *
     * @param index Индекс.
     */
    public void checkIndex(int index) {
        Objects.checkIndex(index, this.size);
    }

    /**
     * Итератор.
     *
     * @return Новый итератор.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            /**
             * Метод проверяет есть ли следующий элемент.
             * Реализовано fail-fast поведение.
             * @return Возвращает true если есть и false если нет.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            /**
             * Метод возвращает первый элемент.
             * @return Элемент.
             */
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

    /**
     * Модель для связанного списка.
     *
     * @param <E> Элемент.
     */
    private static class Node<E> {
        protected E item;
        protected Node<E> next;
    }
}
