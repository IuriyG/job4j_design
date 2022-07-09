package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleArrayTest {
    private final int capacity = 10;
    SimpleArray<Integer> array = new SimpleArray<>(capacity);

    /**
     * Инициализация массива до начала теста.
     */
    @Before
    public void setUp() {
        array.add(8);
        array.add(4);
        array.add(9);
        array.add(33);
    }

    /**
     * Тест метода set.
     */
    @Test
    public void whenUseSet() {
        array.set(3, 8);
        assertEquals(array.get(3), 8);
    }

    /**
     * Тест метода set при условии выхода за границы массива.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseSetAndHaveIOOE() {
        array.set(10, 33);
        assertEquals(array.get(10), 33);
    }

    /**
     * Тест метода add.
     */
    @Test
    public void whenUseAdd() {
        array.add(11);
        assertEquals(array.get(4), 11);
    }

    /**
     * Тест метода remove.
     */
    @Test
    public void whenUseRemove() {
        array.remove(1);
        assertEquals(array.get(1), 9);
    }

    /**
     * Тест метода remove при условии выхода за границы массива.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseRemoveAndHaveIOOE() {
        array.remove(10);
        assertEquals(array.get(1), 2);
    }

    /**
     * Тест метода get.
     */
    @Test
    public void whenUseGet() {
        assertEquals(9, array.get(2));
    }

    /**
     * Тест метода get при условии выхода за границы массива.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseGetAndHaveIOOE() {
        assertEquals(2, array.get(10));
    }
}
