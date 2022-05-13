package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.05.2022
 */
public class Analyze {
    /**
     * Метод считает (за линейное время):
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
        Map<Integer, String> actual = current.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName));
        for (User user : previous) {
            if (actual.containsKey(user.getId()) && !actual.containsValue(user.getName())) {
                change++;
            } else if (!actual.containsKey(user.getId())) {
                count++;
            }
        }
        int deleted = actual.size() - previous.size() + count;
        return new Info(deleted, change, count);
    }
}
