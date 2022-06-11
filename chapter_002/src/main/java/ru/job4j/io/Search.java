package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс осуществляет поиск файлов по определённому предикату.
 * Также проводит валидацию входящих аргументов.
 *
 * @author Iuriy Gaydarzhi.
 * @since 04.06.2022
 */
public class Search {
    /**
     * Основной метод.
     *
     * @param args Входящие аргументы.
     * @throws IOException Исключение ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("c://projects//job4j_design");
        validateFiles(new String[]{String.valueOf(start), ".txt"});
        search(start, p -> p.toFile().getName().endsWith(".txt")).forEach(System.out::println);
    }

    /**
     * Метод проходит по заданному каталогу 'root' и подкаталогам соблюдая условие 'condition'.
     *
     * @param root      Корневая папка.
     * @param condition Условия для поиска файла.
     * @return Возвращает список соответствующих файлов.
     * @throws IOException Исключение ввода-вывода.
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод проверяет входящие аргументы по следующим критериям:
     * наличие двух входящих аргументов;
     * является ли первый аргумент директорией;
     * начинается ли второй аргумент с точки.
     *
     * @param args Входящие аргументы.
     */
    public static void validateFiles(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Требуется два аргумента!");
        }
        if (!new File(args[0]).isDirectory()) {
            throw new IllegalArgumentException("Указан не верно формат директории!");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Расширение должно начинаться с символа - \".\"!");
        }
    }
}
