package io.github.kruzuzdyak.console_lib.storage;

import java.util.List;
import java.util.Optional;

public interface Storage<T> {

    List<T> findAll();

    Optional<T> findByName(String name);

    boolean create(T entity);

    boolean deleteOne(T entity);

    boolean deleteAll(T entity);
}
