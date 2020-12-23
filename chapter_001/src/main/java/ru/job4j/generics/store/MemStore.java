package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();
    private int counter;

    public int searchIdByIndex(String id) {
        int result = -1;
        int index = 0;
        while (index < counter) {
            if (mem.get(index) != null && mem.get(index).getId().equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public void add(T model) {
        mem.add(model);
        counter++;
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = searchIdByIndex(id);
        if (index != -1) {
            mem.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = searchIdByIndex(id);
        if (index != -1) {
            mem.remove(index);
            result = true;
            counter--;
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
