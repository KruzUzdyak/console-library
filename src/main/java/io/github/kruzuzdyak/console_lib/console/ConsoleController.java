package io.github.kruzuzdyak.console_lib.console;

import java.util.HashMap;
import java.util.Map;

public class ConsoleController {

    private final ConsoleReader reader = new ConsoleReader();
    private final ConsoleWriter writer = new ConsoleWriter();
    private final Map<String, Action> actions;

    public ConsoleController() {
        actions = new HashMap<>();
        actions.put("1", BookController.INSTANCE::run);
        actions.put("0", () -> System.exit(0));
    }

    public void run() {
        writer.write("Welcome!");
        while (true) {
            writer.write("1 - books");
            String action = reader.readInput();
            actions.getOrDefault(action, () -> writer.write("Unknown action")).run();
        }
    }
}
