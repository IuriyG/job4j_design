package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Iuriy Gaydarzhi.
 * @since 25.05.2022
 * <p>
 * Класс считывает данные из строкового файла, добавляет из в коллекцию и возвращает значение по ключу.
 */
public class Config {
    /**
     * Файл.
     */
    private final String path;
    /**
     * Коллекция значений.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Конструктор.
     *
     * @param path Входящий аргумент.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Основной класс.
     *
     * @param args Входящий аргумент.
     */
    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

    /**
     * Метод принимает файл строк, считывает его пропуская строку, которая начинается с символа '#' (комментарии).
     * И добавляет в коллекцию пару: ключ - значение.
     */
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            br.lines().filter(s -> !s.startsWith("#")).filter(s -> s.contains("=")).forEach(s -> {
                String[] keyValuePair = s.split("=", 2);
                String key = keyValuePair[0];
                String value = keyValuePair[1];
                if (value.isEmpty() || key.isEmpty()) {
                    throw new IllegalArgumentException("Файл не соответствует условиям шаблона!");
                }
                values.put(key, value);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Сравнивает входящий ключ с ключом из коллекции.
     *
     * @param key Входящий аргумент.
     * @return Возвращает значение по ключу.
     */
    public String value(String key) {
        return values.get(key);
    }

    /**
     * Переопределенный метод.
     *
     * @return Возвращает строки в читабельном виде.
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}