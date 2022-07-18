package ru.job4j.io.serialization;

import java.io.*;

/**
 * Класс демонстрирует работу процесса Сериализации и Десериализаци.
 *
 * @author Iuriy Gaydarzhi.
 * @since 15.07.2022
 */
public class Contact implements Serializable {
    /**
     * Поле класса, исходный файл.
     */
    private static final File FILE = new File("./chapter_002/data/serialization.out");
    /**
     * Поле класса, Имя контакта.
     */
    private final String name;
    /**
     * Поле класса, Фамилия контакта.
     */
    private final String surName;

    /**
     * Конструктор.
     *
     * @param surName Фамилия.
     * @param name    Имя.
     */
    public Contact(String surName, String name) {
        this.surName = surName;
        this.name = name;
    }

    /**
     * Основной метод. Демонстрирует работу методов
     * {@link #serialization(Contact)} и {@link #deSerialization(File)}.
     *
     * @param args Входящие аргументы.
     * @throws IOException            Исключение ввода-вывода.
     * @throws ClassNotFoundException Исключение при отсутствии класса.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact contact = new Contact("Reeves", "Keanu");
        serialization(contact);
        deSerialization(FILE);
    }

    /**
     * Метод сериализация, – процесс преобразования объектов в бинарный
     * (т.е. последовательность битов) или текстовый формат.
     *
     * @param contact Входящий аргумент, Контакт.
     * @throws IOException Исключение ввода-вывода.
     */
    public static void serialization(Contact contact) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
    }

    /**
     * Метод десериализация, – процесс преобразования сериализованных данных в объекты,
     * т.е. операция обратная сериализация.
     *
     * @param file Входящий аргумент, файл.
     * @throws IOException            Исключение ввода-вывода.
     * @throws ClassNotFoundException Исключение при отсутствии класса.
     */
    public static void deSerialization(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream oos = new ObjectInputStream(fis)) {
            Contact contactFromFile = (Contact) oos.readObject();
            System.out.println(contactFromFile);
        }
    }

    /**
     * Переопределенный метод вывода в консоль.
     *
     * @return Форматированный вывод.
     */
    @Override
    public String toString() {
        return "Contact{"
                + "surName: '"
                + surName + '\''
                + ", Name: '"
                + name + '\''
                + '}';
    }
}
