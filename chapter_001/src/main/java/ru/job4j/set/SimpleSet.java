package ru.job4j.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

/**
 * @author Iuriy Gaydarzhi.
 * @since 01.02.2021
 */
public class SimpleSet<E> implements Iterable<E> {
    SimpleArray<E> simpleArray = new SimpleArray<>(10);

    public boolean add(E e) {
        if (e != null && contains(e)) {
            simpleArray.add(e);
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(E e) {
        boolean rsl = true;
        for (E e1 : simpleArray) {
            if (e1.equals(e)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }
}
