package io.github.kruzuzdyak.console_lib.console;

import java.util.function.Predicate;

public class ControllerUtil {

    private static final ConsoleWriter WRITER = ConsoleWriter.INSTANCE;
    private static final ConsoleReader READER = ConsoleReader.INSTANCE;

    public static String requestUserInput(String message, String invalidInputMessage, Predicate<String> validator) {
         while(true) {
             WRITER.print(message);
             String userInput = READER.readLine().trim();
             if (validator.test(userInput)) {
                 return userInput;
             } else {
                 WRITER.print(invalidInputMessage);
             }
         }
    }
}
