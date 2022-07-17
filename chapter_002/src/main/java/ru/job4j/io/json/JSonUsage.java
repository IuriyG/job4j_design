package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.io.serialization.Contact;

/**
 * @author Iuriy Gaydarzhi.
 * @since 17.07.2022
 */
public class JSonUsage {

    public static void main(String[] args) {
        Person person = new Person(true, 90, "Male",
                new Contact("Daniels", "Peter"),
                new String[]{"Книга1", "Книга2", "Книга3"});
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        String newJson = """
                {
                "alive":true,
                "age":90,
                "gender":"Male",
                "contact":{"name":"Peter","surName":"Daniels"},
                "info":["Книга1","Книга2","Книга3"]
                }
                """;

        Person personMod = gson.fromJson(newJson, Person.class);
        System.out.println(personMod);
    }
}
