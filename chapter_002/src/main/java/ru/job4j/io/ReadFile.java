package ru.job4j.io;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @since 20.05.2022
 */
public class ReadFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            List<Character> list = new ArrayList<>();
            System.out.println(Arrays.toString("login=login".getBytes()));

            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
                list.add((char) read);
            }
            System.out.println(text);
            Collections.sort(list);
            System.out.println(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
