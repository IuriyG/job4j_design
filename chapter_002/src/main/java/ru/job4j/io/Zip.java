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
    private static final String DIR = "d";
    private static final String EXC = "e";
    private static final String OUT = "o";

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Аргументов должно быть три!");
        }
        Zip zip = new Zip();
        zip.packSingleFile(new File("./pom.xml"), new File("./pom.zip"));
        ArgsName argsName = ArgsName.of(args);
        String exclude = argsName.get(EXC);
        String directory = argsName.get(DIR);
        Path output = Paths.get(argsName.get(OUT));
        zip.validateArgs(argsName);
        Predicate<Path> predicate = path -> !path.toFile().getName().endsWith(exclude);
        Path source = Paths.get(directory);
        List<Path> list = Search.search(source, predicate);
        zip.packFiles(list, output);
    }

    private void validateArgs(ArgsName args) {
        Path in = Paths.get(args.get(DIR));
        if (!in.toFile().isDirectory()) {
            throw new IllegalArgumentException("Аргумент 'd' не является директорией!");
        }
        if (!args.get(EXC).startsWith(".")) {
            throw new IllegalArgumentException("Аргумент 'e' должен начинаться с точки, затем расширение!");
        }
        if (!args.get(OUT).contains(".zip")) {
            throw new IllegalArgumentException("Аргумент 'o' должен состоять из названия и расширения - '.zip'!");
        }
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