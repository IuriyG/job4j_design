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
        Path start = Paths.get("c://projects//job4j_design");
        validateFiles(new String[]{String.valueOf(start), ".txt"});
        search(start, p -> p.toFile().getName().endsWith(".txt")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateFiles(String[] args) {
        if (args.length == 2) {
            if (!new File(args[0]).isDirectory()) {
                throw new IllegalArgumentException("Указан не верно формат директории!");
            }
            if (!args[1].startsWith(".")) {
                throw new IllegalArgumentException("Расширение должно начинаться с символа - точка!");
            }
        } else {
            throw new IllegalArgumentException("Требуется два аргумента!");
        }
    }
}

