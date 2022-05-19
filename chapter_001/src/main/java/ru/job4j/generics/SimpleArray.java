package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <b>5.2.1. Реализовать SimpleArray<T>.</b>
 * В этом задании необходимо сделать универсальную обертку над массивом.
 * Создать класс: public class SimpleArray<T>
 * Добавить методы: add(T model), set(int index, T model), remove(int index), get(int index).
 * Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор,
 * предназначенный для обхода данной структуры.
 * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
 * Для проверки индекса используйте метод Objects.checkIndex.
 * Для удаления использовать System.arraycopy() вместо цикла.
 *
 * @param <T> Тип параметра.
 */
public class SimpleArray<T> implements Iterable<T> {
    private final Object[] data;
    private int index = 0;

    /**
     * Конструктор.
     *
     * @param capacity Принимает объём создаваемого объекта.
     */
    public SimpleArray(int capacity) {
        this.data = new Object[capacity];
    }

    /**
     * Метод заменяет указанным элементом (model) элемент, находящийся по индексу index.
     *
     * @param index Входящий индекс ячейки массива.
     * @param model Входящий элемент.
     */
    public void set(int index, T model) {
        check(index);
        this.data[index] = model;
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку.
     *
     * @param model Входящий элемент.
     */
    public void add(T model) {
        this.data[index++] = model;
    }

    /**
     * Метод удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо
     * сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек).
     *
     * @param index Входящий индекс ячейки массива.
     */
    public void remove(int index) {
        check(index);
        System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        data[data.length - this.index] = null;
        this.index--;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     *
     * @param index Входящий индекс ячейки массива.
     * @return Элемент.
     */
    public Object get(int index) {
        check(index);
        return this.data[index];
    }

    /**
     * Метод проверяет находиться ли значение index п в границах добавленных элементов.
     *
     * @param index Входящий индекс ячейки массива.
     */
    public void check(int index) {
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
            private int pointer = 0;

            /**
             * Метод проверяет есть ли следующий элемент.
             * @return Возвращает true если есть и false если нет.
             */
            @Override
            public boolean hasNext() {
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
                return (T) data[pointer++];
            }
        };
    }
}
