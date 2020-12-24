package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Каркас универсального хранилища.
 *
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();
    private int counter;

    /**
     * Метод осуществляет поиск id по index в списке mem,
     * в рамках добавленных элементов счетчика counter.
     *
     * @param id Элемент id.
     * @return Если элемент id найден, тогда возвращается сам id, если нет, возвращается -1.
     */
    protected int searchIdByIndex(String id) {
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

    /**
     * Метод добавляет модель данных в список mem.
     * При добавлении 1 элемента также увеличивается счетчик counter на 1 единицу.
     *
     * @param model Модель данных.
     */
    @Override
    public void add(T model) {
        mem.add(model);
        counter++;
    }

    /**
     * Метод меняет модель данных model по найденному id.
     *
     * @param id    Элемент id.
     * @param model Модель данных.
     * @return Если элемент id найден и замена осуществлена, возвращает true, иначе - false.
     */
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

    /**
     * Метод удаляет элемент по id в списке mem также счетчик counter уменьшается на 1 единицу.
     *
     * @param id Элемент id.
     * @return Если элемент id найден, осуществлено удаление по элементу id и
     * возвращается true, иначе false.
     */
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

    /**
     * Метод осуществляет поиск элемента id в списке mem.
     *
     * @param id Элемент id.
     * @return Если элемент найден по id, возвращает id, иначе - null.
     */
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
