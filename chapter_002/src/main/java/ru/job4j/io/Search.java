package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Iuriy Gaydarzhi.
 * @since 04.06.2022
 */
public class Search {

    public static void main(String[] args) throws IOException {
        validateFiles(new File("c:/projects/job4j_design"), ".txt");
        System.out.println();
        Path start = Paths.get("c://projects//job4j_design");
        search(start, p -> p.toFile().getName().endsWith(".txt")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateFiles(File folder, String extension) {
        if (!folder.exists()) {
            throw new IllegalArgumentException("Папка не существует!");
        }
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(extension)) {
                    System.out.println(file);
                }
            }
        }
    }
}

