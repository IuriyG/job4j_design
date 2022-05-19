package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * @author Iuriy Gaydarzhi.
 * @since 01.02.2021
 */
public class SimpleSetTest {
    SimpleSet<String> simpleSet = new SimpleSet<>();

    @Test
    public void testAdd() {
        simpleSet.add("Omaha");
        simpleSet.add(null);
        simpleSet.add("Tacoma");
        simpleSet.add(null);
        simpleSet.add("Tacoma");
        simpleSet.add("Maple V.");
        simpleSet.add(null);

        Iterator<String> it = simpleSet.iterator();
        assertEquals(it.next(), ("Omaha"));
        assertEquals(it.next(), (null));
        assertEquals(it.next(), ("Tacoma"));
        assertEquals(it.next(), ("Maple V."));
    }
}
