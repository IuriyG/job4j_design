package ru.job4j.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 01.02.2021
 *
 * <b>1. Реализовать коллекцию Set на массиве</b>
 * 1. Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать boolean add(E e) и реализовывать Iterable<E>.
 * 2. Написать тесты на наличие дубликатов.
 * 3. Написать тест с учетом null элемента.
 */
public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> simpleArray = new SimpleArray<>(10);

    /**
     * Метод добавляет элементы в список simpleArray.
     *
     * @param e Входящий элемент.
     * @return Возвращает true если элемент добавлен или false если нет.
     */
    public boolean add(E e) {
        if (checkForDuplicates(e)) {
            simpleArray.add(e);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверяет список на отсутствие дубликатов.
     *
     * @param e Входящий элемент.
     * @return Возвращает true если если нет дубликатов и false если найден.
     */
    public boolean checkForDuplicates(E e) {
        boolean rsl = true;
        for (E e1 : simpleArray) {
            if (Objects.equals(e1, e)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    /**
     * Итератор.
     *
     * @return Возвращает итератор класса SimpleArray.
     */
    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }
}
