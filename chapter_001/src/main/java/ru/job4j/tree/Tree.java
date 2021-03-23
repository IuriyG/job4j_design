package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * @author Iuriy Gaydarzhi.
 * @since 23.03.2021
 */
public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }


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

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
