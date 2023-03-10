package io.github.kruzuzdyak.console_lib.storage;

import io.github.kruzuzdyak.console_lib.entity.EBook;

import java.util.List;
import java.util.Optional;

public class EBookStorage implements Storage<EBook> {

    private static final String FILE_NAME = "ebooks.txt";

    @Override
    public List<EBook> findAll() {
        return null;
    }

    @Override
    public Optional<EBook> findByName(String name) {
        return null;
    }

    @Override
    public boolean create(EBook entity) {
        return false;
    }

    @Override
    public boolean deleteOne(EBook entity) {
        return false;
    }

    @Override
    public boolean deleteAll(EBook entity) {
        return false;
    }
}
