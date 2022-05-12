package ru.job4j.question;

import java.util.Objects;
import java.util.Set;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.05.2022
 */
public class Analyze {
    /**
     * Метод считает:
     * сколько добавлено новых пользователей;
     * сколько изменено пользователей;
     * сколько удалено пользователей.
     *
     * @param previous Входящая коллекция с исходными данными.
     * @param current  Входящая коллекция с результирующими данными.
     * @return Результат вычисления.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int change = 0, count = 0;
        for (User user : previous) {
            for (User user1 : current) {
                if (user.getId() == user1.getId() && !Objects.equals(user.getName(), user1.getName())) {
                    change++;
                }
                if (user.getId() == user1.getId()) {
                    count++;
                }
            }
        }
        return new Info(current.size() - count, change, previous.size() - count);
    }
}
