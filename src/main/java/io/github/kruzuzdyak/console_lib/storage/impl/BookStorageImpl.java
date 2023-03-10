package io.github.kruzuzdyak.console_lib.storage.impl;

import io.github.kruzuzdyak.console_lib.entity.Book;
import io.github.kruzuzdyak.console_lib.storage.BookStorage;

public class BookStorageImpl extends AbstractStorage<Book> implements BookStorage {

    private static final String BOOK_FILE_NAME = "books.txt";
    private static final String BOOK_PLACEHOLDER = "%s ||| %s ||| %s\n";
    private static final String SEPARATOR = "\\s\\|{3}\\s";

    public BookStorageImpl() {
        super(BOOK_FILE_NAME);
    }

    @Override
    protected Book convertToEntity(String bookString) {
        String[] attributes = bookString.split(SEPARATOR);
        return new Book(attributes[0], attributes[1], attributes[2]);
    }

    @Override
    protected String convertToString(Book book) {
        return String.format(BOOK_PLACEHOLDER, book.getName(), book.getAuthor(), book.getPublishingYear());
    }
}
