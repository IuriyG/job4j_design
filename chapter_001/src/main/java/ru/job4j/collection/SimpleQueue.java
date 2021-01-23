package ru.job4j.collection;

/**
 * @author Iuriy Gaydarzhi.
 * @since 22.01.2021
 *
 * <b>5. Очередь на двух стеках.</b>
 * В этом задании мы рассмотрим другую организацию данных - очередь.
 * Термин FIFO - first input first output. Первый пришел, первый ушел.
 * <b>Алгоритм.</b>
 * Данные очереди нужно хранить в ru.job4j.collection.SimpleStack. Для этого задания нужны два стека.
 * Представьте, что у вас стопка с тарелками. Вам нужно достать нижнюю тарелку.
 * Для этого вы перекладываете все тарелки в другую стопку.
 * Стопка - это стек. Для операции извлечения первой тарелки нужны две стопки.
 */

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод добавляет элементы в список in, удаляя их списка out, если список out пуст.
     *
     * @return Возвращает первое значение и удаляет его из коллекции
     */
    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.poll());
            }
        }
        return out.poll();
    }

    /**
     * Метод помещает значение в коллекцию.
     *
     * @param value Значение.
     */
    public void push(T value) {
        in.push(value);
    }
}
