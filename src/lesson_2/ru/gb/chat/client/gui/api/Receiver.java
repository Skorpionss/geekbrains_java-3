package lesson_2.ru.gb.chat.client.gui.api;

@FunctionalInterface
public interface Receiver {
    void receive(String data);

}
