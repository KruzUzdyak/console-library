package io.github.kruzuzdyak.console_lib;

import io.github.kruzuzdyak.console_lib.entity.Book;
import io.github.kruzuzdyak.console_lib.storage.BookStorage;

public class Application {

    public static void main(String[] args) throws Exception {
        System.out.println("Initializing...");
        Book book = new Book("1984", "George Orwell", "1948");
        new BookStorage().delete(book);
        System.out.println("Exiting...");
    }
}
