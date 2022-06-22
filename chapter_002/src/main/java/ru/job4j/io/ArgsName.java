package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс принимает массив аргументов, проводит валидацию, разбивает па пару:
 * ключ и значение, затем добавляет в коллекцию.
 *
 * @author Iuriy Gaydarzhi.
 * @since 15.06.2022
 */
public class ArgsName {

    /**
     * Коллекция ключей.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Метод проверяет пуст ли массив, затем вызывает метод 'parse'.
     *
     * @param args Входящий аргументы.
     * @return Результат метода 'parse'.
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Входящий массив пуст!");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    /**
     * Основной метод.
     *
     * @param args Входящий аргументы.
     */
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    /**
     * Метод проверяет аргументы:
     * на наличие первого символа у ключа;
     * на наличие разделяющего символа;
     * на отсутствие ключа;
     * на отсутствие значение у ключа.
     *
     * @param arg Входящие аргументы.
     */
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

    /**
     * Метод преобразует из входящих данных пару: ключ и значение, и добавляет их в коллекцию.
     *
     * @param args Входящие аргументы.
     */
    private void parse(String[] args) {
        for (String arg : args) {
            validateData(arg);
            String[] keyValuePair = arg.split("=", 2);
            String key = keyValuePair[0].substring(1);
            String value = keyValuePair[1];
            values.put(key, value);
        }
    }

    /**
     * Метод проверяет наличие ключа в коллекции, если ключа нет, выбрасывает исключение и уведомляет или ключ.
     *
     * @param key Входящий аргумент, 'ключ'.
     * @return Исключение или ключ.
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Отсутствует ключ!");
        }
        return values.get(key);
    }
}
