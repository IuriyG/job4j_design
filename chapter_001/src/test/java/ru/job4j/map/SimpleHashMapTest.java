package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.02.2021
 */
public class SimpleHashMapTest {
    SimpleHashMap<String, String> map = new SimpleHashMap<>();

    @Before
    public void setUp() {
        map.insert("luly", "BIO. Mate");
        map.insert("lul", "Mate");
        map.insert("bl", "Mite");
        map.insert("bolly", "GO");
    }

    @Test
    public void testInsert() {
        map.insert("bake", "Bro");
        assertThat(map.get("bake"), is("Bro"));
        assertFalse(map.insert("lol", "night"));
    }

    @Test
    public void testGet() {
        map.insert("Luly", "BIO. Mate");
        assertThat(map.get("luly"), is("BIO. Mate"));
        assertThat(map.get("Bio Moon"), is("Нет такого элемента!"));
    }

    @Test
    public void testDelete() {
        assertTrue(map.delete("bolly"));
    }

    @Test
    public void testResize() {
        map.insert("Block", "Ask");
        map.insert("Obs", "ABC");
        map.insert("Bro", "Lion");
        map.insert("No", "Again");
        map.insert("Bob", "Brion");
        map.insert("Noob", "Bio");
        assertSame(map.getSize(), 10);
    }

    @Test
    public void testIterator() {
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("bl"));
        assertThat(it.next(), is("lul"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testHasNextCME() {
        Iterator<String> it = map.iterator();
        map.insert("ubl", "Mie");
        it.next();
    }
}
