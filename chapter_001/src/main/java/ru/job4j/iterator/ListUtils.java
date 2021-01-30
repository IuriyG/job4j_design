package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Iuriy Gaydarzhi.
 * @since 29.01.2021
 *
 * <b>Задание.</b>
 * 1. Реализовать недостающие методы в классе ListUtils:
 * - addAfter();
 * - addBefore();
 * - removeIf(). Запрещено использовать метод List.removeIf;
 * - replaceIf();
 * - removeAll(). Запрещено использовать метод List.removeAll();
 * 2. Допишите тесты на каждый из методов.
 * 3. Залейте код на GitHub.
 * 4. Переведите на ответственного.
 */
public class ListUtils {
    /**
     * Метод вставляет элемент после индекса.
     *
     * @param list  Список элементов.
     * @param index Индекс элемента.
     * @param value Значение элемента.
     * @param <T>   Тип.
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод вставляет элемент до индекса.
     *
     * @param list  Список элементов.
     * @param index Индекс элемента.
     * @param value Значение элемента.
     * @param <T>   Тип.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод удаляет все элементы, соответствующие предикату.
     *
     * @param list   Список элементов.
     * @param filter Условие предиката.
     * @param <T>    Тип.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы, соответствующие предикату.
     *
     * @param list   Список элементов.
     * @param filter Условие предиката.
     * @param value  Значение элемента.
     * @param <T>    Тип.
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.set(value);
            }
        }
    }

    /**
     * Метод удаляет из списка list элементы, которые есть в списке elements.
     *
     * @param list     Список элементов.
     * @param elements Список сравниваемых элементов.
     * @param <T>      Тип.
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (elements.contains(i.next())) {
                i.remove();
            }
        }
    }
}
