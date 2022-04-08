package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Iuriy Gaydarzhi.
 * @since 23.03.2021
 */
public interface SimpleTree<E> {

    /**
     * Метод добавления узлов.
     *
     * @param parent Родительский узел.
     * @param child  Дочерний узел.
     * @return Если узла child нет - добавляет, иначе - false.
     */
    boolean add(E parent, E child);

    /**
     * Метод поиска элемента.
     *
     * @param value Входящий аргумент.
     * @return Если элемент найден возвращает true, иначе false.
     */
    Optional<Node<E>> findBy(E value);

    /**
     * Класс описывающий узел.
     *
     * @param <E>
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        /**
         * Конструктор.
         *
         * @param value Возвращает значение узла.
         */
        public Node(E value) {
            this.value = value;
        }

        /**
         * Геттер
         *
         * @return Возвращает дочерний узел.
         */
        public List<Node<E>> getChildren() {
            return children;
        }
    }
}
