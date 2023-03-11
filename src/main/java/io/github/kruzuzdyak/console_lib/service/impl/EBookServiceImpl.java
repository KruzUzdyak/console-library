package io.github.kruzuzdyak.console_lib.service.impl;

import io.github.kruzuzdyak.console_lib.entity.EBook;
import io.github.kruzuzdyak.console_lib.factory.StorageFactory;
import io.github.kruzuzdyak.console_lib.service.EBookService;
import io.github.kruzuzdyak.console_lib.storage.EBookStorage;

import java.util.List;
import java.util.Optional;

public class EBookServiceImpl implements EBookService {

    private final EBookStorage storage = StorageFactory.INSTANCE.geteBookStorage();

    @Override
    public List<EBook> findAll() {
        return storage.findAll();
    }

    @Override
    public Optional<EBook> findByName(String name) {
        return storage.findByName(name);
    }

    @Override
    public boolean create(EBook ebook) {
        return storage.create(ebook);
    }

    @Override
    public boolean deleteOne(String name) {
        Optional<EBook> book = storage.findByName(name);
        if (book.isPresent()) {
            return storage.deleteOne(book.get());
        }
        return false;
    }

    @Override
    public boolean delete(String name) {
        Optional<EBook> book = storage.findByName(name);
        if (book.isPresent()) {
            return storage.deleteOne(book.get());
        }
        return false;
    }
}
