package ru.job4j.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 01.02.2021
 */
public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> simpleArray = new SimpleArray<>(10);
    private int checkNull = 0;

    public boolean add(E e) {
        if (contains(e)) {
            simpleArray.add(e);
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(E e) {
        boolean rsl = true;
        for (E e1 : simpleArray) {
            if (e == null && checkNull < 1) {
                checkNull++;
                break;
            } else if (e == null && checkNull == 1) {
                rsl = false;
                break;
            }
            if (Objects.requireNonNull(e).equals(e1)) {
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
