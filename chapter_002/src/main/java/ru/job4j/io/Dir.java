package ru.job4j.io;

import java.io.File;
import java.util.Objects;

/**
 * Класс выводит в консоль имена файлов и размер из заданного каталога.
 *
 * @author Iuriy Gaydarzhi.
 * @since 02.06.2022
 */
public class Dir {
    /**
     * Основной класс.
     *
     * @param args Входящий аргумент.
     */
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                System.out.printf("Name: %s Size: %d%n", subfile.getName(), subfile.length());
            }
        }
    }
}
