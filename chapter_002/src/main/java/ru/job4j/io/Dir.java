package ru.job4j.io;

import java.io.File;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 02.06.2022
 */
public class Dir {

    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                System.out.printf("Name: %s", subfile.getName());
                System.out.printf(" Size: %s%n", subfile.length());
            }
        }
    }
}
