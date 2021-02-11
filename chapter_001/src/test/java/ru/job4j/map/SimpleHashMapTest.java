package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.02.2021
 */
public class SimpleHashMapTest {
    SimpleHashMap<Object, Object> map = new SimpleHashMap<>(2);

    @Before
    public void setUp() {
        map.insert(13, "Bro");
        map.insert(32, "BINGO. Mate");
    }

    @Test
    public void testInsert() {
        assertThat(map.hash(13), is(1));
        assertThat(map.hash(32), is(0));
    }

    @Test
    public void testGet() {
        assertThat(map.get(13), is("Bro"));
        assertThat(map.get(32), is("BINGO. Mate"));
    }

    @Test
    public void testDelete() {
        assertTrue(map.delete(13));
    }

    @Test
    public void testSize() {
        map.insert(32, "Bo bo");
        assertThat(map.getSize(), is(4));
    }

    @Test
    public void testHash() {
        assertThat(map.hash(13), is(13 % 2));
    }

    @Test
    public void testCheckIndex() {
        assertTrue(map.checkIndex(13));
    }

    @Test
    public void testIterator() {
        Iterator<Object> it = map.iterator();
        assertThat(it.next(), is("BINGO. Mate"));
        assertThat(it.next(), is("Bro"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNSEE() {
        Iterator<Object> it = map.iterator();
        assertThat(it.next(), is("BINGO. Mate"));
        assertThat(it.next(), is("Bro"));
        assertThat(it.next(), is("Bro"));
    }
}
