package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Iuriy Gaydarzhi.
 * @since 20.05.2022
 * <p>
 * Класс демонстрирует работу класса FileInputStream.
 */
public class EvenNumberFile {
    /**
     * Метод считывает из файла числа и проверяет, являются ли они чётными, если да - то выводит на консоль.
     *
     * @param args Входящий аргумент.
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                if (read % 2 == 0) {
                    System.out.println((char) read);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
