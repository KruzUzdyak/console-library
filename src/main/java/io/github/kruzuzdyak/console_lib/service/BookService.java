package io.github.kruzuzdyak.console_lib.service;

import io.github.kruzuzdyak.console_lib.entity.Book;
import io.github.kruzuzdyak.console_lib.storage.BookStorage;
import io.github.kruzuzdyak.console_lib.factory.StorageFactory;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookStorage storage = StorageFactory.INSTANCE.getBookStorage();

    public List<Book> findAll() {
        return storage.findAll();
    }

    public Optional<Book> findByName(String name) {
        return storage.findByName(name);
    }

    public boolean create(Book book) {
        return storage.create(book);
    }

    public boolean deleteOne(String name) {
        return storage.findByName(name).filter(storage::deleteOne).isPresent();
    }

    public boolean delete(String name) {
        return storage.findByName(name).filter(storage::deleteAll).isPresent();
    }
}
