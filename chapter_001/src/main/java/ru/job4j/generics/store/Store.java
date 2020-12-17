package ru.job4j.generics.store;

/**
 * @author Iuriy Gaydarzhi.
 * @created 16.12.2020
 */

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
