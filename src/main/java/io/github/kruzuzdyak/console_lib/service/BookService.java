package io.github.kruzuzdyak.console_lib.service;

import io.github.kruzuzdyak.console_lib.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findByName(String name);

    boolean create(Book book);

    boolean deleteOne(String name);

    boolean delete(String name);
}
