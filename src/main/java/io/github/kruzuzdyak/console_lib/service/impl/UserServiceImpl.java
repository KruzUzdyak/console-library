package io.github.kruzuzdyak.console_lib.service.impl;

import io.github.kruzuzdyak.console_lib.entity.User;
import io.github.kruzuzdyak.console_lib.factory.StorageFactory;
import io.github.kruzuzdyak.console_lib.service.UserService;
import io.github.kruzuzdyak.console_lib.storage.UserStorage;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserStorage storage = StorageFactory.INSTANCE.getUserStorage();

    @Override
    public List<User> findAll() {
        return storage.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return storage.findByName(name);
    }

    @Override
    public List<String> findAllNames() {
        return storage.findAllNames();
    }

    @Override
    public boolean create(User user) {
        return storage.create(user);
    }

    @Override
    public boolean deleteOne(String name) {
        Optional<User> user = storage.findByName(name);
        if (user.isPresent()) {
            return storage.deleteOne(user.get());
        }
        return false;
    }
}
