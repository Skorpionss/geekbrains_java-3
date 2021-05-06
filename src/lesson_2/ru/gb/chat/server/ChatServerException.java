package lesson_2.ru.gb.chat.server;

public class ChatServerException extends RuntimeException {

    public ChatServerException(String message) {
        super(message);
    }

    public ChatServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
