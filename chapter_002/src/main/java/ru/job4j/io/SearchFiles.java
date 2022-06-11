package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Класс описывает логику поиска фалов при прохождении всей директории включая вложенные.
 *
 * @author Iuriy Gaydarzhi.
 * @since 04.06.2022
 */
public class SearchFiles implements FileVisitor<Path> {
    /**
     * Поле предиката.
     */
    private final Predicate<Path> condition;
    /**
     * Поле для списка файлов.
     */
    private final List<Path> list = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param condition Возвращает предикат.
     */
    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    /**
     * Метод продолжает обход.
     *
     * @param dir   a reference to the directory
     * @param attrs the directory's basic attributes
     * @return Продолжает обход.
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    /**
     * Метод проходит по каталогам, добавляя в список файлы, которые соответствуют условиям предиката.
     *
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return Продолжает обход.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            list.add(file.toAbsolutePath());
        }
        return CONTINUE;
    }

    /**
     * Метод продолжает обход.
     *
     * @param file a reference to the file
     * @param exc  the I/O exception that prevented the file from being visited
     * @return Продолжает обход.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    /**
     * Метод продолжает обход.
     *
     * @param dir a reference to the directory
     * @param exc {@code null} if the iteration of the directory completes without
     *            an error; otherwise the I/O exception that caused the iteration
     *            of the directory to complete prematurely
     * @return Продолжает обход.
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }

    /**
     * Конструктор для поля.
     *
     * @return Возвращает результирующий список.
     */
    public List<Path> getPaths() {
        return list;
    }
}
