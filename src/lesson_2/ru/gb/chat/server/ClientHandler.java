package lesson_2.ru.gb.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private ChatServer chatServer;
    private boolean z;

    public ClientHandler(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ChatServerException("Ошибка в обработчике клиента, передача данных", e);
        }

        new Thread(() -> {
            doAuthentication();
            Listen();
        })
                .start();

    }

    public String getName() {
        return name;
    }

    public void Listen() {
        receiveMessage();
    }

    private void doAuthentication() {
        sendMessage("Введите -auth логин парроль");

        while (true) {
            try {


                String massage = in.readUTF();
                // -auth login password (вид коректного сообщения)
                if (massage.startsWith("-auth")) {
                    String[] massagesplit = massage.split(" ");
                    String login = massagesplit[1];
                    String password = massagesplit[2];

                    Optional<AuthenticationService.Entry> mayBeCredentials
                            = chatServer.getAuthenticationService().findEntryByCredentials(login, password);

                    if (mayBeCredentials.isPresent()) {
                        AuthenticationService.Entry credentials = mayBeCredentials.get();
                        if (!chatServer.isLoggedIn(credentials.getName())) {
                            name = credentials.getName();
                            chatServer.broadcast(String.format("Пользователь с именем %s зашел в чат", name));
                            chatServer.subscribe(this);
                            sendMessage("Добро пожаловать в чат");
                            z = true;
                            return;
                        } else {
                            sendMessage(String.format("Пользователь с именем %s уже залогинен", credentials.getName()));
                        }
                    } else {
                        sendMessage("нет такого логина или пороля");
                    }

                } else {
                    sendMessage("Данные некоректны");
                }

            } catch (IOException e) {
                throw new ChatServerException("Ошибка в получении сообщения", e);
            }
        }
    }

    public void receiveMessage() {
        while (true) {
            try {
                String message = in.readUTF();
                chatServer.broadcast(String.format("%s: %s", name, message));
            } catch (IOException e) {
                throw new ChatServerException("ошибка в получении", e);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new ChatServerException("ошибка передачи сообщения", e);
        }

    }

    public boolean getZ() {
        return z;
    }
}

