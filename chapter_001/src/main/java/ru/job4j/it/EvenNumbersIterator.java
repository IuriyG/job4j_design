package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean checkForEvenness() {
        return data[pointer] % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        for (; pointer < data.length; pointer++) {
            if (checkForEvenness()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        checkForEvenness();
        return data[pointer++];
    }
}
