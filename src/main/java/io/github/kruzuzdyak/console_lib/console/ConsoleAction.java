package io.github.kruzuzdyak.console_lib.console;

@FunctionalInterface
public interface ConsoleAction {

    ConsoleWriter writer = ConsoleWriter.INSTANCE;

    void act();

    static void unknown() {
        writer.print("--- Unknown action ---");
    }
}
