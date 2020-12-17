package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @created 16.12.2020
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int counter = 0;
        boolean result = false;
        for (T t : mem) {
            if (t.getId().equals(id)) {
                mem.set(counter, model);
                result = true;
                break;
            }
            counter++;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        int counter = 0;
        boolean result = false;
        for (T t : mem) {
            if (t.getId().equals(id)) {
                mem.remove(counter);
                result = true;
                break;
            }
            counter++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        for (T t : mem) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
