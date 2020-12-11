package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {
    private final int capacity = 10;
    SimpleArray<Integer> array = new SimpleArray<>(capacity);

    @Before
    public void SetUp() {
        array.add(8);
        array.add(4);
        array.add(9);
        array.add(33);
    }

    @Test
    public void whenUseSet() {
        array.set(3, 8);
        assertThat(array.get(3), is(8));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseSetAndHaveIOOE() {
        array.set(10, 33);
        assertThat(array.get(10), is(33));
    }

    @Test
    public void whenUseAdd() {
        array.add(11);
        assertThat(array.get(4), is(11));
    }

    @Test
    public void whenUseRemove() {
        array.remove(1);
        assertThat(array.get(1), is(9));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseRemoveAndHaveIOOE() {
        array.remove(10);
        assertThat(array.get(1), is(2));
    }

    @Test
    public void whenUseGet() {
        assertThat(9, is(array.get(2)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseGetAndHaveIOOE() {
        assertThat(2, is(array.get(10)));
    }
}
