package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * @author Iuriy Gaydarzhi.
 * @since 29.06.2022
 */
public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
