package io.github.kruzuzdyak.console_lib.storage;

import io.github.kruzuzdyak.console_lib.entity.User;

import java.util.List;
import java.util.Optional;

public class UserStorage implements Storage<User> {

    private static final String FILE_NAME = "users.txt";

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByName(String name) {
        return null;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean deleteOne(User entity) {
        return false;
    }

    @Override
    public boolean deleteAll(User entity) {
        return false;
    }
}
