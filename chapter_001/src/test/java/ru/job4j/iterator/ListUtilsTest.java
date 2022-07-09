package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Iuriy Gaydarzhi.
 * @since 29.01.2021
 */
public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertEquals(Arrays.asList(1, 2, 3), input);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addAfter(input, 1, 3);
        assertEquals(Arrays.asList(1, 2, 3, 4), input);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeIf(input, e -> e > 2);
        assertEquals(Arrays.asList(1, 2), input);
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, e -> e == 3, 99);
        assertEquals(Arrays.asList(1, 2, 99, 4, 5), input);
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> output = new ArrayList<>(Arrays.asList(2, 4, 6));
        ListUtils.removeAll(input, output);
        assertEquals(Arrays.asList(1, 3, 5), input);
    }
}
