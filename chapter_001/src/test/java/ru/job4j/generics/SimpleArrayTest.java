package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {
    SimpleArray<Object> array = new SimpleArray<>(new Object[]{
            1, 2, 3});

    @Test
    public void whenUseSet() {
        SimpleArray<Object> expected = new SimpleArray<>(
                new Object[]{1, 2, new ArrayList<>()}
        );
        array.set(2, new ArrayList<>());
        assertThat(array, is(expected));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenUseSetAndHaveIOOE() {
        SimpleArray<Object> expected = new SimpleArray<>(
                new Object[]{1, 2, new ArrayList<>()}
        );
        array.set(4, new ArrayList<>());
        assertThat(array, is(expected));
    }

    @Test
    public void wheUseAdd() {
        SimpleArray<Object> expected = new SimpleArray<>(
                new Object[]{new ArrayList<>(), 2, 3});
        array.add(new ArrayList<>());
        assertThat(array, is(expected));
    }

    @Test
    public void wheUseRemove() {
        SimpleArray<Object> expected = new SimpleArray<>(
                new Object[]{2, 3, 3});
        array.remove(0);
        assertThat(array, is(expected));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void wheUseRemoveAndHaveIOOE() {
        SimpleArray<Object> expected = new SimpleArray<>(
                new Object[]{2, 3, 3});
        array.remove(4);
        assertThat(array, is(expected));
    }

    @Test
    public void wheUseGet() {
        assertThat(2, is(array.get(1)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void wheUseGetAndHaveIOOE() {
        assertThat(2, is(array.get(4)));
    }
}
