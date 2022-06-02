package ru.job4j.collection;


import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertSame;

/**
 * @author Iuriy Gaydarzhi.
 * @since 04.01.2021
 */
public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertSame(linked.deleteFirst(), 1);
        Iterator<Integer> it = linked.iterator();
        assertSame(it.next(), 2);
    }

    @Test
    public void whenAddThenIterate() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertSame(it.next(), 1);
        assertSame(it.next(), 2);
    }

    @Test
    public void whenAddAndRevertThenIterate() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertSame(it.next(), 2);
        assertSame(it.next(), 1);
    }

    @Test
    public void whenAddAndRevertThenIterateMoreThan2() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertSame(it.next(), 4);
        assertSame(it.next(), 3);
        assertSame(it.next(), 2);
        assertSame(it.next(), 1);
    }

    @Test
    public void whenRevertEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.revert();
    }
}
