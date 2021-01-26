package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <b>5.1.1.Итератор для двухмерного массива int[][].</b>
 * <b>Задание.</b>
 * Нужно с помощью указатель row column двигать указатель.
 * Добавлять новые поля в класс MatrixIt не нужно.
 * Цель итератора переместить указатель на нужную ячейку.
 * Итератор не копирует элементы в новую коллекцию, а перевод указатель.
 * 1. Реализуйте методы next и hasNext.
 * 2. Загрузите код в репозиторий. Оставьте ссылку на коммит.
 * 3. Переведите на ответственного.
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод проверяет, если ли следующий элемент в 2D массиве.
     *
     * @return возвращает результат проверки.
     */
    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (column < data[row].length) {
                return true;
            }
            row++;
            column = 0;
        }
        return false;
    }

    /**
     * Метод возвращает первый элемент 2D массива.
     * Второй вызов метода вернет второй элемент и так далее.
     *
     * @return возвращает результат итерации.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
