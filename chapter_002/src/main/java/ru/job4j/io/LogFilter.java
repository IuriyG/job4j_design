package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @since 20.05.2022
 */
public class LogFilter {

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
    }

    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String read;
            while ((read = in.readLine()) != null) {
                Arrays.stream(read.split(" ")).filter(s -> s.matches("404")).forEach(list::add);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
