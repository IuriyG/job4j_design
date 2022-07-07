package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс имитирует работу бота.
 *
 * @author Iuriy Gaydarzhi.
 * @since 06.07.2022
 */
public class EchoServer {
    /**
     * Метод создает серверный сокет, затем клиентский.
     * Пока сервер открыт принимает запросы.
     * Принимает входной поток и отправляем через буферизированные потоки.
     * Первым сообщением отправляется строка с кодом состояния.
     * Если входящее сообщение содержит значение:
     * <p>{@code "/?msg=Hello"},то отвечает: {@code Hello}.
     * <p>{@code "/?msg=Exit"},то отвечает: {@code Good Bye!} и завершает работу программы.
     * <p>На другие запросы отвечает: {@code What?}.
     * Считывает все входящие строки и выводит их в консоль.
     *
     * @param args Водные данные.
     * @throws IOException Исключение ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("/?msg=Hello")) {
                        out.write("Hello \r\n".getBytes());
                    } else if (str.contains("/?msg=Exit")) {
                        out.write("Good Bye!\r\n".getBytes());
                        server.close();
                    } else {
                        out.write("What?\r\n".getBytes());
                    }
                    for (str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
