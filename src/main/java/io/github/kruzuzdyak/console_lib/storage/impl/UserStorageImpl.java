package io.github.kruzuzdyak.console_lib.storage.impl;

import io.github.kruzuzdyak.console_lib.entity.Role;
import io.github.kruzuzdyak.console_lib.entity.User;
import io.github.kruzuzdyak.console_lib.storage.UserStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UserStorageImpl extends AbstractStorage<User> implements UserStorage {

    private static final String USER_FILE_NAME = "users.txt";
    private static final String USER_PLACEHOLDER = "%s ||| %s ||| %s ||| %s\n";
    private static final String SEPARATOR = "\\s\\|{3}\\s";

    public UserStorageImpl() {
        super(USER_FILE_NAME);
    }

    @Override
    public List<String> findAllNames() {
        List<String> names;
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_NAME))) {
            names = reader.lines()
                    .map(userString -> userString.split(SEPARATOR)[0])
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return names;
    }

    @Override
    public boolean deleteAll(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected User convertToEntity(String userString) {
        String[] attributes = userString.split(SEPARATOR);
        return new User(attributes[0], attributes[1], attributes[2], Role.valueOf(attributes[3]));
    }

    @Override
    protected String convertToString(User user) {
        return String.format(USER_PLACEHOLDER,
                user.getName(), user.getPassword(), user.getEmail(), user.getRole());
    }
}
