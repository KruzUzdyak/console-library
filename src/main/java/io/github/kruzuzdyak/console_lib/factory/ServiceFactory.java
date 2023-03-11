package io.github.kruzuzdyak.console_lib.factory;

import io.github.kruzuzdyak.console_lib.service.BookService;
import io.github.kruzuzdyak.console_lib.service.EBookService;
import io.github.kruzuzdyak.console_lib.service.UserService;
import io.github.kruzuzdyak.console_lib.service.impl.BookServiceImpl;
import io.github.kruzuzdyak.console_lib.service.impl.EBookServiceImpl;
import io.github.kruzuzdyak.console_lib.service.impl.UserServiceImpl;

public class ServiceFactory {

    public static final ServiceFactory INSTANCE = new ServiceFactory();

    private final BookService bookService = new BookServiceImpl();
    private final EBookService eBookService = new EBookServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public BookService getBookService() {
        return bookService;
    }

    public EBookService geteBookService() {
        return eBookService;
    }

    public UserService getUserService() {
        return userService;
    }
}
