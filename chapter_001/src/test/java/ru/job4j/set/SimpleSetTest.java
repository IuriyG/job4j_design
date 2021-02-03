package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

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
        assertThat(it.next(), is("Omaha"));
        assertThat(it.next(), is(nullValue()));
        assertThat(it.next(), is("Tacoma"));
        assertThat(it.next(), is(nullValue()));
        assertThat(it.next(), is("Maple V."));
        assertThat(it.next(), is(nullValue()));
    }
}
