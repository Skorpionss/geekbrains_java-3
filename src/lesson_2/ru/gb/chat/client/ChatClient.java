package lesson_2.ru.gb.chat.client;

import lesson_2.ru.gb.chat.client.gui.ChatFrame;
import lesson_2.ru.gb.chat.client.gui.api.Receiver;
import lesson_2.ru.gb.chat.client.ChatCommunication;

public class ChatClient {
    private final ChatFrame frame;
    private final ChatCommunication communication;

    public ChatClient(String host, int port) {
        communication = new ChatCommunication(host, port);
        frame = new ChatFrame(data -> communication.transmit(data));

        new Thread(() -> {
            Receiver receiver = frame.getReceiver();
            while (true) {
                receiver.receive(communication.receive());
            }
        })
                .start();
    }

}