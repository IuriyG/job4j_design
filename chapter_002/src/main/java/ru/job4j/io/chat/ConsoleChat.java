package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuriy Gaydarzhi.
 * @since 23.06.2022
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    /**
     * Лог диалога между пользователем и ботом.
     */
    private final String path;
    /**
     * Ответы, которые использует бот.
     */
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./chapter_002/data/bot&user_log.txt", "./chapter_002/data/sentence_for_bot.txt");
        cc.run();
    }

    public void run() throws IOException {
        List<String> list = new ArrayList<>();
        String you = "Вы:";
        String bot = "Бот:";
        String talk = "Пообщаемся?";
        String answer = "Хорошо, не буду надоедать!";
        String bye = "Всего доброго, Бро)!";
        boolean execute = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(talk);
        list.add(talk);
        do {
            System.out.println(you);
            list.add(you);
            String word = br.readLine();
            list.add(word);
            if (word.equals(STOP)) {
                System.out.println(bot);
                list.add(bot);
                System.out.println(answer);
                list.add(answer);
                while (execute) {
                    word = br.readLine();
                    list.add(word);
                    if (word.equals(CONTINUE)) {
                        execute = false;
                    }
                }
                execute = true;
            } else if (word.equals(OUT)) {
                System.out.println(bye);
                list.add(bye);
                break;
            }
            System.out.println(bot);
            list.add(bot);
            double random = Math.random() * 50;
            System.out.println(readPhrases().get((int) random));
            list.add(readPhrases().get((int) random));
        } while (true);
        saveLog(list);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.path)))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
