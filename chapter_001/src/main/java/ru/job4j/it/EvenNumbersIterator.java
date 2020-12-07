package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод проверяет числа на четность.
     *
     * @return результат проверки чисел в массиве data.
     */
    public boolean checkForEvenness() {
        return data[pointer] % 2 == 0;
    }

    /**
     * Метод проверяет числа массива data на четность.
     *
     * @return true если четные и false если нечетные.
     */
    @Override
    public boolean hasNext() {
        for (; pointer < data.length; pointer++) {
            if (checkForEvenness()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод возвращает только четные числа.
     *
     * @return четные числа из массива data.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[pointer++];
    }
}
