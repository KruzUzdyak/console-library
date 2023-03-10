package io.github.kruzuzdyak.console_lib.factory;

import io.github.kruzuzdyak.console_lib.storage.BookStorage;
import io.github.kruzuzdyak.console_lib.storage.EBookStorage;
import io.github.kruzuzdyak.console_lib.storage.UserStorage;

public class StorageFactory {

    public static final StorageFactory INSTANCE = new StorageFactory();

    private final BookStorage bookStorage = new BookStorage();
    private final EBookStorage eBookStorage = new EBookStorage();
    private final UserStorage userStorage = new UserStorage();

    public BookStorage getBookStorage() {
        return bookStorage;
    }

    public EBookStorage geteBookStorage() {
        return eBookStorage;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }
}
