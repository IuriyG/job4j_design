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
     * Разделитель. Делит аргумент 'filter' на две части.
     */
    private String[] toggle;
    /**
     * Файл с исходными данными.
     */
    private String file;
    /**
     * Аргумент 'delimiter'.
     * Разделитель. Служит разделителем между словами в результирующем списке или в консоли.
     */
    private String delimiter;
    /**
     * Аргумент 'out'.
     * Конечный файл с результирующими данными или вывод в консоль.
     */
    private String target;

    /**
     * Основной метод.
     *
     * @param args Входящие аргументы.
     * @throws FileNotFoundException Ошибка при вводе-выводе данных.
     */
    public static void main(String[] args) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader();
        csvReader.handle(ArgsName.of(args));
    }

    /**
     * Метод считывает входящие аргументы и назначает их переменным.
     * Вызывает метод {@link #validateKeys()}.
     * Считывает исходный файл добавляя данные во временный список.
     * Вызывает метод 'separateArgs'.
     * Далее зависимости от аргумента {@link #target}, выводит в консоль или взывает метод {@link #separateArgs(List)}.
     *
     * @param argsName Входящие аргументы.
     * @throws FileNotFoundException Ошибка при отсутствии файла.
     */
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

    /**
     * Метод создает временный список в котором находятся только фильтры.
     * Затем находит индекс перового и второго аргумента. Далее присваивает их соответствующим временным переменным.
     * В цикле считывает строку из входящего списка и передает во временный список,
     * назначая каждому слову разделённому символом ';' свой индекс.
     * Потом используя временные переменные в качестве индекса добавляет слова в новый список,
     * разделяя их {@link #delimiter}.
     * @param args Список с данными.
     */
    private void separateArgs(List<String> args) {
        List<String> strings = new ArrayList<>(List.of(args.get(0).split(";")));
        int fArg = strings.indexOf(toggle[0]);
        int sArg = strings.indexOf(toggle[1]);
        for (String arg : args) {
            List<String> s = List.of(arg.split(";"));
            this.result.add(s.get(fArg) + this.delimiter + s.get(sArg));
        }
    }

    /**
     * Метод проводит валидацию данных согласно условию задания:
     * расширение исходного файла должно быть - '.csv';
     * аргумент 'delimiter', должен быть указан как - ';';
     * аргумент 'out' имеет только два возможный вариантна:
     * 1. Для вывода в консоль указать в качестве параметра - 'stdout';
     * 2. Для записи в файл, указать название файла с таким же расширением, как и источник;
     * Количество фильтров должно быть два.
     */
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

    /**
     * Метод записывает результирующий список {@link #target} в файл {@link #target}.
     * @param log Список с данными.
     */
    private void resultLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.target)))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
