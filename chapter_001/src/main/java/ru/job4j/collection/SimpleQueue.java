package ru.job4j.collection;

/**
 * @author Iuriy Gaydarzhi.
 * @since 22.01.2021
 */

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.poll());
            }
        }
        return out.poll();
    }

    public void push(T value) {
        in.push(value);
    }
}
