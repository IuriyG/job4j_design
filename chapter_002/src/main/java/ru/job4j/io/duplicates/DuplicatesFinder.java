package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс демонстрирует работу класса DuplicatesVisitor.
 *
 * @author Iuriy Gaydarzhi.
 * @since 07.06.2022
 */
public class DuplicatesFinder {
    /**
     * Основной метод.
     *
     * @param args Входящий аргумент.
     * @throws IOException Исключение ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("c://projects//job4j_design"), visitor);
        visitor.getDuplicates().forEach(System.out::println);
    }
}
