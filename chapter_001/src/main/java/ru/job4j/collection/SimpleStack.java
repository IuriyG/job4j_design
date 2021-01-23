package ru.job4j.collection;

/**
 * @author Iuriy Gaydarzhi.
 * @since 19.01.2021
 *
 * <b>4. Используя контейнер на базе связанного списка создать контейнер Stack.</b>
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
    private int size;

    /**
     * Метод возвращает последнее значение и удаляет его из коллекции.
     *
     * @return Значение.
     */
    public T pop() {
        size--;
        return linked.deleteLast();
    }

    /**
     * Метод помещает значение в коллекцию.
     *
     * @param value Значение.
     */
    public void push(T value) {
        size++;
        linked.add(value);
    }

    /**
     * Метод возвращает первое значение и удаляет его из коллекции.
     *
     * @return Значение.
     */
    public T poll() {
        size--;
        return linked.deleteFirst();
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
