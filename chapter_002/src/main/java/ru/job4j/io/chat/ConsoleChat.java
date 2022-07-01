package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс имитирует работу консольного чата.
 *
 * @author Iuriy Gaydarzhi.
 * @since 23.06.2022
 */
public class ConsoleChat {
    /**
     * Команда для бота. При вводе слова, программа прекращает работу.
     */
    private static final String OUT = "закончить";
    /**
     * Команда для бота. При вводе слова, бот замолкает.
     */
    private static final String STOP = "стоп";
    /**
     * Команда для бота. При вводе слова, бот продолжает работать.
     */
    private static final String CONTINUE = "продолжить";
    /**
     * Список для временного хранилища диалога.
     */
    private final List<String> list = new ArrayList<>();
    /**
     * Лог диалога между пользователем и ботом.
     */
    private final String path;
    /**
     * Ответы, которые использует бот.
     */
    private final String botAnswers;

    /**
     * Конструктор.
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Основной метод. Демонстрирует работу метода run.
     *
     * @param args Взводящие аргументы.
     * @throws IOException Ошибка при вводе-выводе данных.
     */
    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./chapter_002/data/bot&user_log.txt", "./chapter_002/data/sentence_for_bot.txt");
        cc.run();
    }

    /**
     * Метод считывает фразы пользователя из консоли. Затем отвечает случайными предложениями из списка.
     * Если пользователь вводит слово 'стоп', бот умолкает пока не будет введено ключевое слово 'продолжить'.
     * Если ввести слово 'закончить' программа прекратит работу.
     *
     * @throws IOException Ошибка при вводе-выводе данных.
     */
    public void run() throws IOException {
        List<String> randomSentence = readPhrases();
        var you = "Вы:";
        var bot = "Бот:";
        var talk = "Пообщаемся?";
        var answer = "Хорошо, не буду надоедать!";
        var bye = "Всего доброго, Бро)!";
        String word;
        boolean execute = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(talk);
        this.list.add(talk);
        do {
            System.out.println(you);
            word = br.readLine();
            this.list.add(you + System.lineSeparator() + word);
            if (STOP.equals(word)) {
                System.out.println(bot);
                this.list.add(bot);
                System.out.println(answer);
                this.list.add(answer);
                while (execute) {
                    word = br.readLine();
                    this.list.add(word);
                    if (CONTINUE.equals(word)) {
                        execute = false;
                    }
                }
                execute = true;
            } else if (OUT.equals(word)) {
                System.out.println(bye);
                this.list.add(bye);
                break;
            }
            System.out.println(bot);
            this.list.add(bot);
            double random = Math.random() * 50;
            System.out.println(randomSentence.get((int) random));
            this.list.add(randomSentence.get((int) random));
        } while (true);
        saveLog(this.list);
    }

    /**
     * Метод считывает предложения из файла и добавляет из в список.
     *
     * @return Список с фразами.
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод записывает в файл диалог пользователя и бота в соответствующем порядке.
     *
     * @param log Принимает список, временное хранилище.
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.path)))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
