package io.github.kruzuzdyak.console_lib.console;

import io.github.kruzuzdyak.console_lib.factory.ControllerFactory;

import java.util.HashMap;
import java.util.Map;

public class ConsoleController {

    private final ConsoleReader reader = new ConsoleReader();
    private final ConsoleWriter writer = new ConsoleWriter();
    private final Map<String, ConsoleAction> actions;

    private boolean active = true;

    public ConsoleController() {
        actions = new HashMap<>();
        actions.put("1", ControllerFactory.INSTANCE.getBookController()::run);
        actions.put("0", () -> active = false);
    }

    public void run() {
        writer.print("Welcome!");
        while (active) {
            writer.print("1 - books, 0 - exit");
            String action = reader.readLine();
            actions.getOrDefault(action, ConsoleAction::unknown).act();
        }
    }
}
