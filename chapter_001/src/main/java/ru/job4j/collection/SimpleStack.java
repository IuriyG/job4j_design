package ru.job4j.collection;

/**
 * @author Iuriy Gaydarzhi.
 * @since 19.01.2021
 *
 * 1. Реализуйте класс Stack.
 * Метод pop() - должен возвращать значение и удалять его из коллекции.
 * Метод push(T value) - помещает значение в коллекцию.
 * Внутри класса SimpleStack есть поле ForwardLinked. Это связанный список из предыдущего задания.
 * Мы можем добавить значение в ForwardLinked. Вам нужно в этот класс добавить метод deleteLast().
 * Чтобы реализовать метод SimpleStack.pop.
 * <b>Задание.</b>
 * 1. Напишите класс SimpleStack.
 */
public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     *
     * @return Значение.
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * Метод помещает значение в коллекцию
     *
     * @param value Значение.
     */
    public void push(T value) {
        linked.add(value);
    }
}
