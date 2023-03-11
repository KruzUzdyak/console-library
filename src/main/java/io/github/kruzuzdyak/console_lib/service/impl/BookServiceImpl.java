package io.github.kruzuzdyak.console_lib.service.impl;

import io.github.kruzuzdyak.console_lib.entity.Book;
import io.github.kruzuzdyak.console_lib.service.BookService;
import io.github.kruzuzdyak.console_lib.storage.BookStorage;
import io.github.kruzuzdyak.console_lib.factory.StorageFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BookServiceImpl implements BookService {

    private final BookStorage storage = StorageFactory.INSTANCE.getBookStorage();

    @Override
    public List<Book> findAll() {
        return storage.findAll();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return storage.findByName(name);
    }

    @Override
    public boolean create(Book book) {
        return storage.create(book);
    }

    @Override
    public boolean deleteOne(String name) {
        Optional<Book> book = storage.findByName(name);
        if (book.isPresent()) {
            return storage.deleteOne(book.get());
        }
        return false;
    }

    @Override
    public boolean delete(String name) {
        Optional<Book> book = storage.findByName(name);
        if (book.isPresent()) {
            return storage.deleteOne(book.get());
        }
        return false;
    }
}
