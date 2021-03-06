package ru.job4j.generics;

/**
 * Модель данных для Animal.
 */
public class Animal {
    String gender;
    int age;

    public Animal(String gender, int age) {
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "gender='" + gender + '\''
                + ", age=" + age
                + '}';
    }
}
