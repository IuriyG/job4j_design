package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <b>Задание.</b>
 * 1. Реализуйте итератор для массива. Итератор должен отдавать элементы в обратном порядке.
 * 2. Загрузите код в репозиторий. Оставьте ссылку на коммит.
 * 3. Переведите на ответственного.
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return возвращает результат проверки.
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод next возвращает первый элемент ячейки. Второй вызов метода next вернет второй элемент и так далее.
     * Метод next сдвигает указатель итератора. Указатель - это ссылка на элемент, который нужно вернуть.
     *
     * @return возвращает результат выполнения метода.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[(data.length - 1) - point++];
    }
}
