package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Iuriy Gaydarzhi.
 * @since 25.05.2022
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            br.lines().filter(s -> s.contains("=")).forEach(s -> {
                String[] keyValuePair = s.split("=", 2);
                String key = keyValuePair[0];
                String value = keyValuePair[1];
                if (value.isEmpty() || key.isEmpty()) {
                    System.out.println("Файл не соответствует условиям шаблона!");
                }
                values.put(key, value);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}