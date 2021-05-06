package lesson_2.ru.gb.chat.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getInstance() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/maxim",
                    "maxim",
                    "maxim"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
