package io.github.kruzuzdyak.console_lib.console;

import java.util.Scanner;

public class ConsoleReader {

    public static final ConsoleReader INSTANCE = new ConsoleReader();

    private final Scanner scanner = new Scanner(System.in);

    private ConsoleReader() {

    }

    public String readLine(){
        return scanner.nextLine();
    }
}
