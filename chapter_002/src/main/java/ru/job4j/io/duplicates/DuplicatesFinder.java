package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Iuriy Gaydarzhi.
 * @since 07.06.2022
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }
}
