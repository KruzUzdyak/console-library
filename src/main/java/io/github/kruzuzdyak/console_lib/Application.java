package io.github.kruzuzdyak.console_lib;

import io.github.kruzuzdyak.console_lib.console.ConsoleController;

public class Application {

    public static void main(String[] args) throws Exception {
        ConsoleController app = new ConsoleController();
        app.run();
    }
}
