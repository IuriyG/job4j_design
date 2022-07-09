package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        assertSame(map.get("bake"), "Bro");
        map.insert("bake", "night");
        assertSame(map.get("bake"), "night");
    }

    @Test
    public void testGet() {
        map.insert("Luly", "BIO. Mate");
        assertSame(map.get("luly"), "BIO. Mate");
        assertNull(map.get("Bio Moon"));
    }

    @Test
    public void testDelete() {
        assertTrue(map.delete("bolly"));
        assertFalse(map.delete("boy"));
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
        assertSame(it.next(), "bl");
        assertSame(it.next(), "lul");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testHasNextCME() {
        Iterator<String> it = map.iterator();
        map.insert("ubl", "Mie");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextNSEE() {
        SimpleHashMap<String, Integer> temp = new SimpleHashMap<>();
        Iterator<String> it = temp.iterator();
        it.next();
    }
}
