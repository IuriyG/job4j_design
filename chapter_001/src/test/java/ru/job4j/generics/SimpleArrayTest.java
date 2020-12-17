package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(array.get(3), is(8));
    }

    /**
     * Тест метода set при условии выхода за границы массива.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseSetAndHaveIOOE() {
        array.set(10, 33);
        assertThat(array.get(10), is(33));
    }

    /**
     * Тест метода add.
     */
    @Test
    public void whenUseAdd() {
        array.add(11);
        assertThat(array.get(4), is(11));
    }

    /**
     * Тест метода remove.
     */
    @Test
    public void whenUseRemove() {
        array.remove(1);
        assertThat(array.get(1), is(9));
    }

    /**
     * Тест метода remove при условии выхода за границы массива.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseRemoveAndHaveIOOE() {
        array.remove(10);
        assertThat(array.get(1), is(2));
    }

    /**
     * Тест метода get.
     */
    @Test
    public void whenUseGet() {
        assertThat(9, is(array.get(2)));
    }

    /**
     * Тест метода get при условии выхода за границы массива.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseGetAndHaveIOOE() {
        assertThat(2, is(array.get(10)));
    }
}
