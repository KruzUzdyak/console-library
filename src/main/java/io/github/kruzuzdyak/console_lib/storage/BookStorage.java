package io.github.kruzuzdyak.console_lib.storage;

import io.github.kruzuzdyak.console_lib.entity.Book;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookStorage implements Storage<Book> {

    private static final String FILE_NAME = "books.txt";
    private static final String TEMP_FILE_NAME = "books_temp.txt";
    private static final String BOOK_PLACEHOLDER = "%s ||| %s ||| %s\n";
    private static final String SEPARATOR = " \\|{3} ";

    @Override
    public List<Book> findAll() {
        List<Book> books;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            books = reader.lines()
                    .map(BookStorage::parseBook)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Optional<Book> findByName(String name) {
        Optional<Book> book;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            book = reader.lines()
                    .filter(bookString -> bookString.contains(name))
                    .map(BookStorage::parseBook)
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void create(Book book) {
        String bookToWrite = String.format(BOOK_PLACEHOLDER, book.getName(), book.getAuthor(), book.getPublishingYear());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(bookToWrite);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Book book) {
        File bookFile = new File(FILE_NAME);
        File tempFile = new File(TEMP_FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(bookFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String current;
            String bookToDelete =
                    String.format(BOOK_PLACEHOLDER, book.getName(), book.getAuthor(), book.getPublishingYear()).trim();
            while ((current = reader.readLine()) != null){
                if (!current.trim().equals(bookToDelete)){
                    writer.append(current).append('\n');
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookFile.delete();
        tempFile.renameTo(bookFile);
    }

    private static Book parseBook(String bookString) {
        String[] attributes = bookString.split(SEPARATOR);
        return new Book(attributes[0], attributes[1], attributes[2]);
    }
}
