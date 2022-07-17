package ru.job4j.io.json;

import ru.job4j.io.serialization.Contact;

import java.util.Arrays;

/**
 * @author Iuriy Gaydarzhi.
 * @since 16.07.2022
 */
public class Person {
    private final boolean alive;
    private final int age;
    private final String gender;
    private final Contact contact;
    private final String[] info;

    public Person(boolean alive, int age, String gender, Contact contact, String[] info) {
        this.alive = alive;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Person{"
                + "Alive:" + alive
                + ", Age:" + age
                + ", Gender:'" + gender
                + '\''
                + ", Contact:" + contact
                + ", Info:" + Arrays.toString(info)
                + '}';
    }
}
