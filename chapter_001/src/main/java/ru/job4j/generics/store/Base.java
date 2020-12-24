package ru.job4j.generics.store;

/**
 * Базовая модель данных.
 *
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */
public abstract class Base {
    private final String id;

    /**
     * Конструктор.
     *
     * @param id Элемент id.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Геттер.
     *
     * @return Элемент id.
     */
    public String getId() {
        return id;
    }
}
