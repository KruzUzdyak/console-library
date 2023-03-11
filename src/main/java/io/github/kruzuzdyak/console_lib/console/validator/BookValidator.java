package io.github.kruzuzdyak.console_lib.console.validator;

public class BookValidator {

    private static final String NAME_PATTERN = "[\\w\\s-]+";
    private static final String AUTHOR_NAME_PATTERN = "[\\w\\s-']+";
    private static final String YEAR_PATTERN = "\\d{3,4}";

    public boolean validateName(String name) {
        return name.matches(NAME_PATTERN);
    }

    public boolean validateAuthor(String authorName) {
        return authorName.matches(AUTHOR_NAME_PATTERN);
    }

    public boolean validatePublishingYear(String publishingYear) {
        return publishingYear.matches(YEAR_PATTERN);
    }
}
