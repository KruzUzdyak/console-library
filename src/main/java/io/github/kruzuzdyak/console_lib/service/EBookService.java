package io.github.kruzuzdyak.console_lib.service;

import io.github.kruzuzdyak.console_lib.entity.EBook;

import java.util.List;
import java.util.Optional;

public interface EBookService {

    List<EBook> findAll();

    Optional<EBook> findByName(String name);

    boolean create(EBook ebook);

    boolean deleteOne(String name);

    boolean delete(String name);
}
