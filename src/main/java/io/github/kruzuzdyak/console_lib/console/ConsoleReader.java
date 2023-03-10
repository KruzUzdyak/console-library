package io.github.kruzuzdyak.console_lib.console;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readInput(){
        return scanner.nextLine();
    }
}
