package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @since 20.05.2022
 * <p>
 * Класс считывает строки из файла по условию, наличие символов - 404, и возвращает список с результатом.
 */
public class LogFilter {
    /**
     * Метод main.
     *
     * @param args Входящий аргумент.
     */
    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
    }

    /**
     * Метод записывает данные из переданного файла в другой переданный файл.
     *
     * @param log  Входящий аргумент.
     * @param file Входящий аргумент.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод считывает строки из файла по условию метода 'has404'.
     *
     * @param file Входящий аргумент.
     * @return Возвращает список.
     */
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(this::has404).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод проверяет есть ли значение '404' в строке.
     *
     * @param file Входящий аргумент.
     * @return Если есть возвращает true или false.
     */
    public boolean has404(String file) {
        boolean rsl = false;
        for (String s : file.split(" ")) {
            if (s.matches("404")) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}
