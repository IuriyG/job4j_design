package ru.job4j.generics.store;

/**
 * Базовая модель store.
 *
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */

public interface Store<T extends Base> {
    /**
     * Метод добавляет элемент.
     *
     * @param model Модель данных.
     */
    void add(T model);

    /**
     * Метод меняет модель данных по элементу.
     *
     * @param id    Элемент id.
     * @param model Модель данных.
     * @return Результат замены.
     */
    boolean replace(String id, T model);

    /**
     * Метод удаляет элемент.
     *
     * @param id Элемент id.
     * @return Результат удаления.
     */
    boolean delete(String id);

    /**
     * Метод ищет по элементу.
     *
     * @param id Элемент id.
     * @return Результат поиска.
     */
    T findById(String id);
}
