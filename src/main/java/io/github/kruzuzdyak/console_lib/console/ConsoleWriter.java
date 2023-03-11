package io.github.kruzuzdyak.console_lib.console;

import java.util.List;

public class ConsoleWriter {

    public static final ConsoleWriter INSTANCE = new ConsoleWriter();

    private ConsoleWriter() {

    }

    public void print(String message) {
        System.out.println(message);
    }

    public <T> void print(List<T> items) {
        items.forEach(System.out::println);
    }
}
