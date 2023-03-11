package io.github.kruzuzdyak.console_lib.console;

import io.github.kruzuzdyak.console_lib.console.validator.EBookValidator;
import io.github.kruzuzdyak.console_lib.entity.EBook;
import io.github.kruzuzdyak.console_lib.factory.ServiceFactory;
import io.github.kruzuzdyak.console_lib.service.EBookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.github.kruzuzdyak.console_lib.console.ControllerUtil.requestUserInput;

public class EBookController {

    private static final String INTERFACE_MESSAGE =
            """
                     1 - list all ebooks
                     2 - find ebook by name
                     3 - add new book
                     4 - remove one book by name
                     5 - remove all books by name
                     0 - back
                    """;

    private final ConsoleWriter writer = ConsoleWriter.INSTANCE;
    private final ConsoleReader reader = ConsoleReader.INSTANCE;
    private final EBookService service = ServiceFactory.INSTANCE.geteBookService();
    private final EBookValidator validator = new EBookValidator();
    private final Map<String, ConsoleAction> actions;

    private boolean active = true;

    public EBookController() {
        actions = new HashMap<>();
        actions.put("1", this::listAllEBooks);
        actions.put("2", this::findByName);
        actions.put("3", this::createNewEBook);
        actions.put("4", this::deleteOneEBookByName);
        actions.put("5", this::deleteAllEBooksByName);
        actions.put("0", () -> active = false);
    }

    public void run() {
        writer.print("--- EBook Interface ---");
        while (active) {
            writer.print(INTERFACE_MESSAGE);
            String action = reader.readLine();
            actions.getOrDefault(action, ConsoleAction::unknown).act();
        }
    }

    private void listAllEBooks() {
        List<String> ebooks = service.findAll().stream()
                .map(this::mapToString)
                .toList();
        writer.print(ebooks);
    }

    private void findByName() {
        String name = requestUserInput("Input ebook name", "Invalid name", validator::validateName);
        Optional<EBook> searchResult = service.findByName(name);

        searchResult.ifPresentOrElse(
                (book) -> writer.print(mapToString(book)),
                () -> writer.print("No ebooks found")
        );
    }

    private void createNewEBook() {
        String name = requestUserInput("Input ebook name", "Invalid book name", validator::validateName);
        String author = requestUserInput("Input ebook author name", "Invalid author name", validator::validateAuthor);
        String year = requestUserInput("Input publishing year", "Invalid year value", validator::validatePublishingYear);
        String url = requestUserInput("Input ebook url", "Invalid URL", validator::validateUrl);
        EBook ebook = new EBook(name, author, year, url);

        if (service.create(ebook)) {
            writer.print("Successfully created");
        } else {
            writer.print("Creation failure");
        }
    }

    private void deleteOneEBookByName() {
        String name = requestUserInput("Input ebook name to delete", "Invalid name", validator::validateName);

        if (service.deleteOne(name)) {
            writer.print("Successfully deleted");
        } else {
            writer.print("Deletion error");
        }
    }

    private void deleteAllEBooksByName() {
        String name = requestUserInput("Input ebook name to delete", "Invalid name", validator::validateName);

        if (service.delete(name)) {
            writer.print("Successfully deleted");
        } else {
            writer.print("Deletion error");
        }
    }

    private String mapToString(EBook eBook) {
        return String.format("Found: %s - %s - %s - %s\n",
                eBook.getName(), eBook.getAuthor(), eBook.getPublishingYear(), eBook.getUrl());
    }
}
