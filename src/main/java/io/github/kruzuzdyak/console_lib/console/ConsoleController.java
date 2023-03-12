package io.github.kruzuzdyak.console_lib.console;

import io.github.kruzuzdyak.console_lib.factory.ControllerFactory;

import java.util.HashMap;
import java.util.Map;

public class ConsoleController {

    private final ConsoleReader reader = ConsoleReader.INSTANCE;
    private final ConsoleWriter writer = ConsoleWriter.INSTANCE;
    private final Map<String, ConsoleAction> actions;

    private boolean active = true;

    public ConsoleController() {
        actions = new HashMap<>();
        actions.put("1", ControllerFactory.INSTANCE.getBookController()::run);
        actions.put("2", ControllerFactory.INSTANCE.geteBookController()::run);
        actions.put("3", ControllerFactory.INSTANCE.getUserController()::run);
        actions.put("0", () -> active = false);
    }

    public void run() {
        writer.print("Welcome!");
        while (active) {
            writer.print("1 - books, 2 - ebooks, 3 - users, 0 - exit");
            String action = reader.readLine();
            actions.getOrDefault(action, ConsoleAction::unknown).act();
        }
    }
}
