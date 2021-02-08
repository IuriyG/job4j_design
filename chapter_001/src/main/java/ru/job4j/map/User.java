package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Iuriy Gaydarzhi.
 * @since 09.02.2021
 *
 * <b>1. Создать модель User</b>
 * Создать модель User и три поля.
 */
public class User {
    private final Calendar birthday;
    private final String name;
    private final int children;

    public User(Calendar birthday, String name, int children) {
        this.birthday = birthday;
        this.name = name;
        this.children = children;
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2001, Calendar.SEPTEMBER, 9);
        User user = new User(calendar, "Ann", 19);
        User user2 = new User(calendar, "Ann", 19);
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> entry : map.entrySet()) {
            User u = entry.getKey();
            Object o = entry.getValue();
            System.out.println(u);
            System.out.println(o);
        }
    }
}
