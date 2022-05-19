package io;

import java.io.FileOutputStream;

/**
 * @author Iuriy Gaydarzhi.
 * @since 19.05.2022
 * <p>
 * Класс демонстрирует работу класса FileOutputStream.
 */
public class ResultFile {
    /**
     * Метод создает файл и записывает в него таблицу умножения.
     *
     * @param args Входящий аргумент.
     */
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] table = new int[9][9];
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    table[j][i] = (i + 1) * (j + 1);
                    out.write(String.valueOf(table[j][i]).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
