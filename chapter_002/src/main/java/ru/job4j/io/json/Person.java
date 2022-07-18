package ru.job4j.io.json;

import ru.job4j.io.serialization.Contact;

import java.util.Arrays;

/**
 * Класс - Модель данных Личность.
 *
 * @author Iuriy Gaydarzhi.
 * @since 16.07.2022
 */
public class Person {
    /**
     * Статус личности.
     */
    private final boolean alive;
    /**
     * Возраст личности.
     */
    private final int age;
    /**
     * Пол личности.
     */
    private final String gender;
    /**
     * Объект {@linkplain Contact}.
     */
    private final Contact contact;
    /**
     * Информация и книгах.
     */
    private final String[] info;

    /**
     * Конструктор.
     *
     * @param alive   Статус.
     * @param age     Возраст.
     * @param gender  Пол.
     * @param contact Контакт.
     * @param info    Информация.
     */
    public Person(boolean alive, int age, String gender, Contact contact, String[] info) {
        this.alive = alive;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.info = info;
    }

    /**
     * Переопределенный метод вывода в консоль.
     *
     * @return Форматированный вывод.
     */
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
