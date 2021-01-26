package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <b>5.1.4. FlatMap для Iterator<Iterator>.</b>
 * Класс FlatMap принимает объект вложенных итераторов.
 * В классе нужно реализовать два метода: next и hasNext.
 * Метод next должен последовательно вернуть числа из вложенных итераторов.
 * В этом задании нельзя копировать элементы во временный список.
 * <b>Задание.</b>
 * 1. Допишите методы next и hasNext.
 * 2. Загрузите код в репозиторий. Оставьте ссылку на коммит.
 * 3. Переведите на ответственного.
 *
 * @param <T>
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * В Stream API есть метод flatMap. Он позволяет получить из элемента потока другой поток.
     * В этом задании Вам нужно создать подобное поведение.
     * Демонстрация работы метода.
     *
     * @param args входящие аргументы.
     */
    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return результат проверки.
     */
    @Override
    public boolean hasNext() {
        while (data.hasNext() && !cursor.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    /**
     * Метод последовательно возвращает числа из вложенных итераторов.
     *
     * @return результат проверки.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}
