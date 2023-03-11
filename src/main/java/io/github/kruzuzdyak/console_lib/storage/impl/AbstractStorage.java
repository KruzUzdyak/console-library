package io.github.kruzuzdyak.console_lib.storage.impl;

import io.github.kruzuzdyak.console_lib.storage.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractStorage<T> implements Storage<T> {

    protected final String FILE_NAME;

    protected AbstractStorage(String fileName) {
        this.FILE_NAME = fileName;
    }

    @Override
    public List<T> findAll() {
        List<T> entities;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            entities = reader.lines()
                           .map(this::convertToEntity)
                           .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    @Override
    public Optional<T> findByName(String name) {
        Optional<T> entity;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            entity = reader.lines()
                         .filter(bookString -> bookString.contains(name))
                         .map(this::convertToEntity)
                         .findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public boolean create(T entity) {
        String entityToWrite = convertToString(entity);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(entityToWrite);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOne(T entity) {
        File currentFile = new File(FILE_NAME);
        File tempFile = new File("temp_" + FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(currentFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            boolean isDeleted = false;
            String current;
            String bookToDelete = convertToString(entity);

            while ((current = reader.readLine()) != null) {
                if (current.trim().equals(bookToDelete) && !isDeleted) {
                    isDeleted = true;
                    continue;
                }
                writer.append(current).append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currentFile.delete() && tempFile.renameTo(currentFile);
    }

    @Override
    public boolean deleteAll(T entity) {
        File currentFile = new File(FILE_NAME);
        File tempFile = new File("temp_" + FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(currentFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String current;
            String bookToDelete = convertToString(entity);
            while ((current = reader.readLine()) != null) {
                if (!current.trim().equals(bookToDelete)) {
                    writer.append(current).append('\n');
                }
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currentFile.delete() && tempFile.renameTo(currentFile);
    }

    protected abstract T convertToEntity(String entityString);

    protected abstract String convertToString(T entity);
}
