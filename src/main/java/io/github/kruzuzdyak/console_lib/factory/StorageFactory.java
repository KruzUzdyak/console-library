package io.github.kruzuzdyak.console_lib.factory;

import io.github.kruzuzdyak.console_lib.storage.BookStorage;
import io.github.kruzuzdyak.console_lib.storage.EBookStorage;
import io.github.kruzuzdyak.console_lib.storage.UserStorage;
import io.github.kruzuzdyak.console_lib.storage.impl.BookStorageImpl;
import io.github.kruzuzdyak.console_lib.storage.impl.EBookStorageImpl;
import io.github.kruzuzdyak.console_lib.storage.impl.UserStorageImpl;

public class StorageFactory {

    public static final StorageFactory INSTANCE = new StorageFactory();

    private final BookStorage bookStorage = new BookStorageImpl();
    private final EBookStorage eBookStorage = new EBookStorageImpl();
    private final UserStorage userStorage = new UserStorageImpl();

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
