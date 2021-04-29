package ru.gb.chat.client;

public class ChatStarter {
    public static void run() {
        run("localhost", 8000);
    }

    public static void run(String host, int port) {
        new ChatClient(host, port);
    }
}
