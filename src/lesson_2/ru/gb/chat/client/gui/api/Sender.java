package lesson_2.ru.gb.chat.client.gui.api;

@FunctionalInterface
public interface Sender {
    void send(String data);
}
