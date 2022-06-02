package ru.job4j.io;

import java.io.*;

/**
 * Преобразование данных одного файла в другой на примере анализа файла регистрации событий сервера.
 *
 * @author Iuriy Gaydarzhi.
 * @since 31.05.2022
 */
public class Analyzes {
    /**
     * Основной класс.
     *
     * @param args Входящий аргумент.
     */
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод считывает файл регистрации событий сервера.
     * Находит диапазоны, когда сервер не работал и записывает время в файл.
     * Например:
     * 200 10:56:01
     * 500 10:57:01
     * 400 10:58:01
     * 200 10:59:01
     * 500 11:01:02
     * 200 11:02:02
     * тут два периода - 10:57:01 до 10:59:01 и 11:01:02 до 11:02:02.
     * Поэтому результат должен быть:
     * 10:57:01;10:59:01;
     * 11:01:02;11:02:02;
     * Начальное время - это время когда статус 400 или 500.
     * Конечное время это когда статус меняется с 400 или 500 на 200 или 300.
     *
     * @param source файл с исходными данными.
     * @param target файл с результатами.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pr = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String line;
            boolean marker = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("400") || line.contains("500")) {
                    if (!marker) {
                        String[] in = line.split(" ");
                        pr.print(in[1] + ";");
                        marker = true;
                    }
                } else if (marker) {
                    String[] in = line.split(" ");
                    pr.println(in[1] + ";");
                    marker = false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
