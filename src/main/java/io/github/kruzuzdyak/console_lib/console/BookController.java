package io.github.kruzuzdyak.console_lib.console;

import io.github.kruzuzdyak.console_lib.entity.Book;
import io.github.kruzuzdyak.console_lib.factory.ServiceFactory;
import io.github.kruzuzdyak.console_lib.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookController {

    private static final String INTERFACE_MESSAGE =
        """
             1 - list all books
             2 - find book by name
             3 - add new book
             4 - remove one book by name
             5 - remove
             0 - back
            """;

    private final ConsoleWriter writer = new ConsoleWriter();
    private final ConsoleReader reader = new ConsoleReader();
    private final BookService service = ServiceFactory.INSTANCE.getBookService();
    private final Map<String, ConsoleAction> actions;

    private boolean active = true;

    public BookController() {
        actions = new HashMap<>();
        actions.put("1", this::listAllBooks);
        actions.put("2", this::findByName);
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
                                 .collect(Collectors.toList());
        writer.printTable(books);
    }

    private void findByName() {
        writer.print("Input name:");
        String name = reader.readLine();
        Optional<Book> searchResult = service.findByName(name);

        searchResult.ifPresentOrElse(
            (book) -> writer.print(mapToString(book)),
            () -> writer.print("No books found")
        );
    }

    private String mapToString(Book book) {
        return String.format("Found: %s - %s - %s\n", book.getName(), book.getAuthor(), book.getPublishingYear());
    }
}
