package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс архивирует папку и все содержимое: файлы, подкаталоги, всё, кроме файлов-исключений.
 * Исключения, задается в параметрах.
 * Параметры для запуска метода: -d=C:\projects\job4j_design -e=.java -o=project.zip
 * Значение ключей:
 * -d - директория, которую мы хотим архивировать.
 * -e - исключаем файлы с расширением - '.java'.
 * -o - название конечного архива.
 *
 * @author Iuriy Gaydarzhi.
 * @since 20.06.2022
 */
public class Zip {
    /**
     * Директория с которой начнется архивирование.
     */
    private static final String DIR = "d";
    /**
     * Исключаемые файлы.
     */
    private static final String EXC = "e";

    /**
     * Конечный архив.
     */
    private static final String OUT = "o";

    /**
     * Основной метод.
     * Проверяет наличие трёх аргументов во входящих данных.
     * Архивирует один файл с заданными данными.
     * Добавляет входные данные в коллекцию.
     * Возвращает все три аргумента в нужном формате.
     * Проводит валидацию входных данных.
     * Создает предикат и необходимый формат для дальнейшего использования.
     * Создает список файлов для архиваций.
     * Создает необходимый нам архив.
     *
     * @param args Входящие аргументы. Установлены в параметрах запуска метода 'main'.
     * @throws IOException Исключение ввода-вывода.
     */
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

    /**
     * Метод проводит валидацию входных данных:
     * существует ли архивируемая директория;
     * начинается ли расширения исключаемого файла с точки;
     * название конечного файла должен содержать - '.zip'.
     *
     * @param args Входящие данные.
     */
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

    /**
     * Метод считывает и архивирует файлы.
     *
     * @param sources Директория и исключаемые файлы.
     * @param target  Конечный архив.
     */
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

    /**
     * Метод считывает и архивирует один файл.
     *
     * @param source Директория и исключаемые файлы.
     * @param target Конечный архив.
     */
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