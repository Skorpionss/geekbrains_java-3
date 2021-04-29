package ru.gb.chat.server;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthenticationService {

    private static final Set<Entry> entries = new HashSet<>();

    private void addSetEntry() {
        entries.add(new Entry("User1", "l1", "p1"));
        entries.add(new Entry("User2", "l2", "p2"));
        entries.add(new Entry("User3", "l3", "p3"));
    }

    public Optional<Entry> findEntryByCredentials(String login, String password) {

        addSetEntry();

        return entries.stream()
                .filter(entry -> entry.getLogin().equals(login) && entry.getPassword().equals(password))
                .findFirst();
    }

    public static class Entry {
        private String name;
        private String login;
        private String Password;

        public Entry(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.Password = password;
        }

        public String getName() {
            return name;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return Password;
        }
    }
}
