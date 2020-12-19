package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @created 16.12.2020
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();
    private int counter;

    public boolean searchById(String id) {
        counter = 0;
        for (T t : mem) {
            if (t.getId().equals(id)) {
                return true;
            }
            this.counter++;
        }
        return false;
    }

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (searchById(id)) {
            mem.set(this.counter, model);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (searchById(id)) {
            mem.remove(this.counter);
        }
        return true;
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
