package ru.job4j.io.serialization;

import java.io.*;

/**
 * @author Iuriy Gaydarzhi.
 * @since 15.07.2022
 */
public class Contact implements Serializable {
    private static final File FILE = new File("./chapter_002/data/serialization.out");
    private final String name;
    private final String surName;

    public Contact(String surName, String name) {
        this.surName = surName;
        this.name = name;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact contact = new Contact("Reeves", "Keanu");
        serialization(contact);
        deSerialization(FILE);
    }

    public static void serialization(Contact contact) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
    }

    public static void deSerialization(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream oos = new ObjectInputStream(fis)) {
            Contact contactFromFile = (Contact) oos.readObject();
            System.out.println(contactFromFile);
        }
    }

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
