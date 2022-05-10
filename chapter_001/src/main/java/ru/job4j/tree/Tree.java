package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * @author Iuriy Gaydarzhi.
 * @since 23.03.2021
 */
public class Tree<E> implements SimpleTree<E> {
    /**
     * Поле ля класса.
     */
    private final Node<E> root;

    /**
     * Конструктор.
     *
     * @param root Входящий аргумент.
     */
    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод осуществляет поиск-сравнение элементов по условию предиката.
     *
     * @param condition Входящий предикат.
     * @return Если элемент найден или соответствует предикату, возвращает элемент или true, иначе - false.
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве.
     *
     * @return Если элементов 2 возвращает true, иначе false.
     */
    public boolean isBinary() {
        return findByPredicate(el -> el.children.size() > 2).isEmpty();
    }

    /**
     * Метод осуществляет поиск элемента по значению value.
     *
     * @param value Входящий аргумент.
     * @return Если элемент найден возвращает true, иначе false.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.value.equals(value));
    }

    /**
     * Метод находит узел по значению parent и добавляет дочерний узел со значением child.
     *
     * @param parent Родительский узел.
     * @param child  Дочерний узел.
     * @return Если узла child нет - добавляет, иначе - false.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> p = findBy(parent);
        Optional<Node<E>> c = findBy(child);
        if (p.isPresent()) {
            if (c.isEmpty()) {
                Node<E> newChild = new Node<>(child);
                p.get().getChildren().add(newChild);
                rsl = true;
            }
        }
        return rsl;
    }
}
