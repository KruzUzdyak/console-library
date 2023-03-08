package io.github.kruzuzdyak.console_lib.storage;

import java.util.List;
import java.util.Optional;

public interface Storage<T> {

    List<T> findAll();

    Optional<T> findByName(String name);

    void create(T entity);

    void delete(T entity);
}
