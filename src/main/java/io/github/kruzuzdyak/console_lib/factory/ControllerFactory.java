package io.github.kruzuzdyak.console_lib.factory;

import io.github.kruzuzdyak.console_lib.console.BookController;

public class ControllerFactory {

    public static final ControllerFactory INSTANCE = new ControllerFactory();

    private final BookController bookController = new BookController();

    public BookController getBookController() {
        return bookController;
    }

}
