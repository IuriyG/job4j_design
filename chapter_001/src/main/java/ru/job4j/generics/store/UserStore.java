package ru.job4j.generics.store;

/**
 * Имплементированный каркас.
 *
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */
public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    /**
     * Метод добавляет модель данных.
     *
     * @param model Модель данных.
     */
    @Override
    public void add(User model) {
        store.add(model);
    }

    /**
     * Метод осуществляет замену модели данных по элементу.
     *
     * @param id    Элемент id.
     * @param model Модель данных.
     * @return Результат замены.
     */
    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    /**
     * Метод удаляет элемент.
     *
     * @param id Элемент id.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    /**
     * Метод ищет по элементу.
     *
     * @param id Элемент id.
     * @return Результат поиска.
     */
    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
