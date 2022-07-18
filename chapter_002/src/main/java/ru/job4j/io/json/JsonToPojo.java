package ru.job4j.io.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.io.serialization.Contact;

/**
 * @author Iuriy Gaydarzhi.
 * @since 18.07.2022
 */
public class JsonToPojo {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("""
                {"name":"Peter","surName":"Daniels"}
                """);

        String[] array = new String[]{"Книга1", "Книга2", "Книга3"};
        JSONArray jsonArray = new JSONArray(array);

        Person person = new Person(true, 90, "Male",
                new Contact("Daniels", "Peter"),
                new String[]{"Книга1", "Книга2", "Книга3"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("alive", person.isAlive());
        jsonObject.put("age", person.getAge());
        jsonObject.put("gender", person.getGender());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("info", jsonArray);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(person));
    }
}
