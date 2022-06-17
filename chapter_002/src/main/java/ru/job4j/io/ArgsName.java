package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Iuriy Gaydarzhi.
 * @since 15.06.2022
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Входящий массив пуст!");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private void validateData(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Отсутствует первый символ ключа - \"-\"");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Отсутствует разделяющий символ - \"=\"!");
        }
        if (arg.startsWith("-=")) {
            throw new IllegalArgumentException("Присутствует только первый символ ключа!");
        }
        String[] keyValuePair = arg.split("=", 2);
        if (keyValuePair[1].isEmpty()) {
            throw new IllegalArgumentException("Отсутствует значение ключа!");
        }
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validateData(arg);
            String[] keyValuePair = arg.split("=", 2);
            String key = keyValuePair[0].substring(1);
            String value = keyValuePair[1];
            values.put(key, value);
        }
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Отсутствует ключ!");
        }
        return values.get(key);
    }
}
