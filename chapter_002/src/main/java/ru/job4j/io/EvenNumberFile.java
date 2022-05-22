package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            List<Integer> list = new ArrayList<>();
            String read;
            while ((read = br.readLine()) != null) {
                if (Integer.parseInt(read) % 2 == 0) {
                    list.add(Integer.parseInt(read));
                }
            }
            System.out.println(list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
