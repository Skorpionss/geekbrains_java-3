package ru.gb.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChatServer {
    private final AuthenticationService authenticationService;
    private final Set<ClientHandler> loggedClients;

    public ChatServer() {
        authenticationService = new AuthenticationService();
        loggedClients = new HashSet<>();
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
            }
        } catch (IOException e) {
            throw new ChatServerException("Ошибка", e);
        }
    }

    public synchronized AuthenticationService getAuthenticationService() {
        return authenticationService;
    }


    public synchronized void subscribe(ClientHandler clientHandler) {
        loggedClients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        loggedClients.remove(clientHandler);
    }

    public synchronized void broadcast(String message) {
        for (ClientHandler clientHandler : loggedClients) {
            clientHandler.sendMessage(message);
        }
    }


    public synchronized boolean isLoggedIn(String name) {
        return loggedClients.stream()
                .filter(client -> client.getName().equals(name))
                .findFirst()
                .isPresent();
    }
}

