package ru.job4j.it;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BackwardArrayItTest {
    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertSame(it.next(), 3);
        assertSame(it.next(), 2);
        assertSame(it.next(), 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{}
        );
        it.next();
    }
}
