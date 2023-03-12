package io.github.kruzuzdyak.console_lib.service;

import io.github.kruzuzdyak.console_lib.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByName(String name);

    List<String> findAllNames();

    boolean create(User user);

    boolean deleteOne(String name);
}
