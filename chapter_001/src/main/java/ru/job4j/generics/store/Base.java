package ru.job4j.generics.store;

/**
 * @author Iuriy Gaydarzhi.
 * @created 16.12.2020
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
