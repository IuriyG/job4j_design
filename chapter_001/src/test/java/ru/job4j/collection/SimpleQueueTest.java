package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author Iuriy Gaydarzhi.
 * @since 22.01.2021
 */
public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        int rsl = queue.poll();
        assertEquals(rsl, 1);
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertEquals(rsl, 1);
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertEquals(rsl, 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }
//    @Test
//    public void whenPushPushPollAndPush() {
//        SimpleQueue<Integer> queue = new SimpleQueue<>();
//        queue.push(1);
//        queue.push(2);
//        queue.poll();
//        queue.push(3);
//        assertSame(queue.poll(),2);
//    }
}