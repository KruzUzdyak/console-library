package io.github.kruzuzdyak.console_lib.console;

import io.github.kruzuzdyak.console_lib.console.validator.UserValidator;
import io.github.kruzuzdyak.console_lib.entity.Role;
import io.github.kruzuzdyak.console_lib.entity.User;
import io.github.kruzuzdyak.console_lib.factory.ServiceFactory;
import io.github.kruzuzdyak.console_lib.service.UserService;

import java.util.*;

import static io.github.kruzuzdyak.console_lib.console.ControllerUtil.requestUserInput;

public class UserController {

    private static final String INTERFACE_MESSAGE =
            """
                    1 - list all users
                    2 - find user by name
                    3 - add new user
                    4 - delete user
                    0 - back
                    """;

    private final ConsoleWriter writer = ConsoleWriter.INSTANCE;
    private final ConsoleReader reader = ConsoleReader.INSTANCE;
    private final UserService service = ServiceFactory.INSTANCE.getUserService();
    private final UserValidator validator = new UserValidator();
    private final Map<String, ConsoleAction> actions;

    private boolean active;

    public UserController() {
        actions = new HashMap<>();
        actions.put("1", this::listAllUsers);
        actions.put("2", this::findByName);
        actions.put("3", this::createNewUser);
        actions.put("4", this::deleteByName);
        actions.put("0", () -> active = false);
    }

    public void run() {
        active = true;
        writer.print("--- User Interface ---");
        while (active) {
            writer.print(INTERFACE_MESSAGE);
            String action = reader.readLine();
            actions.getOrDefault(action, ConsoleAction::unknown).act();
        }
    }

    private void listAllUsers() {
        List<String> users = service.findAll().stream()
                .map(this::mapToString)
                .toList();
        writer.print(users);
    }

    private void findByName() {
        String name = requestUserInput("Input user name", "Invalid name", validator::validateName);
        Optional<User> searchResult = service.findByName(name);

        searchResult.ifPresentOrElse(
                (user) -> writer.print(mapToString(user)),
                () -> writer.print("No books found")
        );
    }

    private void createNewUser() {
        List<String> presentNames = service.findAllNames();
        String name = requestUserInput("Input new user name", "Invalid name or already present",
                (input) -> validator.validateName(input, presentNames));
        String password = requestUserInput("Input user password (contains one upper, one lover case letters and one digit)",
                "Invalid password", validator::validatePassword);
        password = Base64.getEncoder().encodeToString(password.getBytes());
        String email = requestUserInput("Input email", "Invalid email", validator::validateEmail);
        String role = requestUserInput("Input role (USER, ADMIN)", "Invalid role", validator::validateRole);
        User user = new User(name, password, email, Role.valueOf(role));

        if (service.create(user)) {
            writer.print("Successfully created");
        } else {
            writer.print("Creation failure");
        }
    }

    private void deleteByName() {
        String name = requestUserInput("Input user name", "Invalid name", validator::validateName);

        if (service.deleteOne(name)) {
            writer.print("Successfully deleted");
        } else {
            writer.print("Deletion error");
        }
    }

    private String mapToString(User user) {
        return String.format("Found: %s - %s - %s\n",
                user.getName(), user.getEmail(), user.getRole());
    }
}
