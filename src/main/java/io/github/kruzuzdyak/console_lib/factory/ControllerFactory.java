package io.github.kruzuzdyak.console_lib.factory;

import io.github.kruzuzdyak.console_lib.console.BookController;
import io.github.kruzuzdyak.console_lib.console.EBookController;
import io.github.kruzuzdyak.console_lib.console.UserController;

public class ControllerFactory {

    public static final ControllerFactory INSTANCE = new ControllerFactory();

    private final BookController bookController = new BookController();
    private final EBookController eBookController = new EBookController();
    private final UserController userController = new UserController();

    public BookController getBookController() {
        return bookController;
    }

    public EBookController geteBookController() {
        return eBookController;
    }

    public UserController getUserController() {
        return userController;
    }
}
