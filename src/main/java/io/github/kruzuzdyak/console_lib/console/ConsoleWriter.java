package io.github.kruzuzdyak.console_lib.console;

import java.util.List;
import java.util.function.Function;

public class ConsoleWriter {

    public void print(String message) {
        System.out.println(message);
    }

    public <T> void printTable(List<T> items) {
        items.stream()
            .forEach(System.out::println);
    }
}
