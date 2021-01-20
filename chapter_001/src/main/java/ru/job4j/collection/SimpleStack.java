package ru.job4j.collection;

/**
 * @author Iuriy Gaydarzhi.
 * @since 19.01.2021
 */
public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}
