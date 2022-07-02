package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс считывает данные с файла с расширением '.csv'. И выводит в консоль или
 * в другой файл с таким же расширением, в зависимости от входных аргументов.
 *
 * @author Iuriy Gaydarzhi.
 * @since 29.06.2022
 */
public class CSVReader {
    /**
     * Временный список, куда помещается данные, считанные из файла '.csv'.
     */
    private final List<String> temp = new ArrayList<>();
    /**
     * Результирующий список, куда записываются результат работы методов.
     */
    private final List<String> result = new ArrayList<>();
    /**
     * Разделитель.
     */
    private String[] toggle;
    private String file;
    private String delimiter;
    private String target;

    public static void main(String[] args) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader();
        csvReader.handle(ArgsName.of(args));
    }

    public void handle(ArgsName argsName) throws FileNotFoundException {
        file = argsName.get("path");
        delimiter = argsName.get("delimiter");
        target = String.valueOf(Path.of(argsName.get("out")));
        toggle = argsName.get("filter").split(",");
        validateKeys();

        Scanner scanner = new Scanner(new File(this.file));
        while (scanner.hasNext()) {
            String sc = scanner.nextLine();
            temp.add(sc);
        }
        separateArgs(temp);
        if ("stdout".equals(target)) {
            result.forEach(System.out::println);
        } else {
            resultLog(result);
        }
    }

    private void separateArgs(List<String> args) {
        List<String> strings = new ArrayList<>(List.of(args.get(0).split(";")));
        int fArg = strings.indexOf(toggle[0]);
        int sArg = strings.indexOf(toggle[1]);
        for (String arg : args) {
            List<String> s = List.of(arg.split(";"));
            this.result.add(s.get(fArg) + this.delimiter + s.get(sArg));
        }
    }

    private void validateKeys() {
        if (!new File(this.file).getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Аргумент 'path' указан не верно!");
        }
        if (!";".equals(this.delimiter)) {
            throw new IllegalArgumentException("Аргумент 'delimiter' указан не верно!");
        }
        if (!new File(this.target).getName().endsWith(".csv") && !"stdout".equals(this.target)) {
            throw new IllegalArgumentException("Аргумент 'out' указан не верно!" + System.lineSeparator()
                    + "Укажите верно конечный файл, или ключевое слово: 'stdout'!");
        }
        if (this.toggle.length != 2) {
            throw new IllegalArgumentException("Аргументов 'filter' должно быть два!");
        }
    }

    private void resultLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.target)))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
