package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Iuriy Gaydarzhi.
 * @since 20.06.2022
 */
public class Zip {

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(new File("./pom.xml"), new File("./pom.zip"));
        ArgsName argsName = ArgsName.of(args);
        String s = argsName.get("e");
        Predicate<Path> predicate = path -> !path.toFile().getName().endsWith(s);
        String s1 = argsName.get("d");
        Path source = Paths.get(s1);
        List<Path> list = Search.search(source, predicate);
        Path target = Paths.get(argsName.get("o"));
        zip.packFiles(list, target);
    }

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}