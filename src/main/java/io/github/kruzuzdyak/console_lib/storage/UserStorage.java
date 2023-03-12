package io.github.kruzuzdyak.console_lib.storage;

import io.github.kruzuzdyak.console_lib.entity.User;

import java.util.List;

public interface UserStorage extends Storage<User> {

    List<String> findAllNames();
}
