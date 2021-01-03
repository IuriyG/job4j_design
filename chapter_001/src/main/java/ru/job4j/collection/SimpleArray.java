package ru.job4j.collection;

import java.util.*;

/**
 * @author Iuriy Gaydarzhi.
 * @since 26.12.2020
 * <>Задание.</b>
 * 1. Реализовать динамический массив.
 * В этом задании мы создадим реализацию ArrayList. ArrayList - это массив.
 * Когда элементов становиться больше чем ячеек в массиве ArrayList создает новый массив с большим размером.
 * Внутри контейнер должен базироваться на массиве Object[] container.
 * Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 * Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.
 * Теперь, поговорим об итераторе. Итератор кидает два исключения:
 * 1) NoSuchElementException. Если итератор "уперся", т.е.  * нет больше элементов, а клиент вызвал этот метод,
 * то этим исключение мы ему подчеркиваем, что элементов больше нет.
 * 2) ConcurrentModificationException. Чтобы кинуть это исключение заводят отдельную переменную в итераторе
 * expectedModCount = modCount и проверяют условие if (expectedModCount != modCount). Если условие выполнено,
 * значит на момент итерирования была изменена коллекция, поэтому вылетает исключение.
 * Это называется fail-fast поведение.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int index = 0;
    private int modCount;

    /**
     * Конструктор.
     *
     * @param capacity Размер массива.
     */
    public SimpleArray(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     *
     * @param index Входящий индекс ячейки массива.
     * @return Элемент.
     */
    public T get(int index) {
        checkIndex(index);
        return (T) this.container[index];
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку.
     *
     * @param model Входящий элемент.
     */
    public void add(T model) {
        modCount++;
        if (index == container.length) {
            newContainer();
        }
        this.container[index++] = model;
    }

    /**
     * Метод создает новый массив н основе старого,но увеличивая размерность в 2 раза.
     */
    private void newContainer() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    /**
     * Метод проверяет находится ли значение index в границах добавленных элементов.
     *
     * @param index Входящий индекс ячейки массива.
     */
    public void checkIndex(int index) {
        Objects.checkIndex(index, this.index);
    }

    /**
     * Итератор.
     *
     * @return Новый итератор.
     */
    @Override
    public Iterator<T> iterator() {

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
                return pointer < index;
            }

            /**
             * Метод возвращает первый элемент.
             * @return Элемент ячейки.
             */
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