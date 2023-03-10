package io.github.kruzuzdyak.console_lib.factory;

import io.github.kruzuzdyak.console_lib.service.BookService;

public class ServiceFactory {

    public static final ServiceFactory INSTANCE = new ServiceFactory();

    private final BookService bookService = new BookService();

    public BookService getBookService() {
        return bookService;
    }
}
