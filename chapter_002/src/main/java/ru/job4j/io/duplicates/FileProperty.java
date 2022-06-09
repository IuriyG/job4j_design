package ru.job4j.io.duplicates;

import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 07.06.2022
 */
public class FileProperty {
    /**
     * Размер файла.
     */
    private final long size;

    /**
     * Название файла.
     */
    private final String name;

    /**
     * Конструктор.
     *
     * @param size Принимает размер файла.
     * @param name Принимает название файла.
     */
    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileProperty{"
                + "size="
                + size
                + ", name='"
                + name
                + '\''
                + '}';
    }

    /**
     * Переопределенный метод.
     *
     * @param o Входящий аргумент.
     * @return Возвращает результат сравнения объектов.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    /**
     * Переопределенный метод.
     *
     * @return Возвращает результат хэш-кода объектов.
     */
    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }
}
