package io.github.kruzuzdyak.console_lib.console;

import io.github.kruzuzdyak.console_lib.console.validator.BookValidator;
import io.github.kruzuzdyak.console_lib.entity.Book;
import io.github.kruzuzdyak.console_lib.factory.ServiceFactory;
import io.github.kruzuzdyak.console_lib.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.github.kruzuzdyak.console_lib.console.ControllerUtil.requestUserInput;

public class BookController {

    private static final String INTERFACE_MESSAGE =
            """
                     1 - list all books
                     2 - find book by name
                     3 - add new book
                     4 - remove one book by name
                     5 - remove all books by name
                     0 - back
                    """;

    private final ConsoleWriter writer = ConsoleWriter.INSTANCE;
    private final ConsoleReader reader = ConsoleReader.INSTANCE;
    private final BookService service = ServiceFactory.INSTANCE.getBookService();
    private final BookValidator validator = new BookValidator();
    private final Map<String, ConsoleAction> actions;

    private boolean active = true;

    public BookController() {
        actions = new HashMap<>();
        actions.put("1", this::listAllBooks);
        actions.put("2", this::findByName);
        actions.put("3", this::createNewBook);
        actions.put("4", this::deleteOneBookByName);
        actions.put("5", this::deleteAllBooksByName);
        actions.put("0", () -> active = false);
    }

    public void run() {
        writer.print("--- Book Interface ---");
        while (active) {
            writer.print(INTERFACE_MESSAGE);
            String action = reader.readLine();
            actions.getOrDefault(action, ConsoleAction::unknown).act();
        }
    }

    private void listAllBooks() {
        List<String> books = service.findAll().stream()
                .map(this::mapToString)
                .toList();
        writer.print(books);
    }

    private void findByName() {
        String name = requestUserInput("Input book name", "Invalid name", validator::validateName);
        Optional<Book> searchResult = service.findByName(name);

        searchResult.ifPresentOrElse(
                (book) -> writer.print(mapToString(book)),
                () -> writer.print("No books found")
        );
    }

    private void createNewBook() {
        String name = requestUserInput("Input book name", "Invalid book name", validator::validateName);
        String author = requestUserInput("Input book author name", "Invalid author name", validator::validateAuthor);
        String year = requestUserInput("Input publishing year", "Invalid year value", validator::validatePublishingYear);
        Book book = new Book(name, author, year);

        if (service.create(book)) {
            writer.print("Successfully created");
        } else {
            writer.print("Creation failure");
        }
    }

    private void deleteOneBookByName() {
        String name = requestUserInput("Input book name to delete", "Invalid name", validator::validateName);

        if (service.deleteOne(name)) {
            writer.print("Successfully deleted");
        } else {
            writer.print("Deletion error");
        }
    }

    private void deleteAllBooksByName() {
        String name = requestUserInput("Input book name to delete", "Invalid name", validator::validateName);

        if (service.delete(name)) {
            writer.print("Successfully deleted");
        } else {
            writer.print("Deletion error");
        }
    }

    private String mapToString(Book book) {
        return String.format("Found: %s - %s - %s\n",
                book.getName(), book.getAuthor(), book.getPublishingYear());
    }
}
