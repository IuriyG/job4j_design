package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertSame;

/**
 * @author Iuriy Gaydarzhi.
 * @since 02.01.2021
 */
public class SimpleListTest {
    SimpleList<String> linked = new SimpleList<>();

    @Before
    public void setUp() {
        linked.add("first");
        linked.add("second");
        linked.add("third");
        linked.add("fourth");
    }

    @Test
    public void testAdd() {
        linked.add("fifth");
        assertSame(linked.get(4), "fifth");
    }

    @Test
    public void testGet() {
        assertSame(linked.get(2), "third");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckIndex() {
        assertSame(linked.get(4), "fourth");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testHasNextCME() {
        Iterator<String> it = linked.iterator();
        linked.add("fifth");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextNSEE() {
        SimpleList<String> list = new SimpleList<>();
        list.iterator().next();
    }
}
