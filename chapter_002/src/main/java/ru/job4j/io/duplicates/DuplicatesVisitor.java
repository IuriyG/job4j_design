package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Iuriy Gaydarzhi.
 * @since 07.06.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(332, "file4DuplicatesFinder.txt");
        if (file.getFileName().toString().equals(fp.getName()) && attrs.size() == fp.getSize()) {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
