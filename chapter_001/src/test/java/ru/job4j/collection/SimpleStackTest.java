package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertSame;

/**
 * @author Iuriy Gaydarzhi.
 * @since 19.01.2021
 */
public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertSame(stack.pop(), 1);
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertSame(stack.pop(), 2);
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertSame(stack.pop(), 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pop();
        assertSame(stack.pop(), 1);
    }
}