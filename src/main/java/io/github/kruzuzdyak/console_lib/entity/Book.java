package io.github.kruzuzdyak.console_lib.entity;

import java.util.Objects;

public class Book extends AbstractBook {

    private String publishingYear;

    public Book(String title, String author, String publishingYear) {
        super(title, author);
        this.publishingYear = publishingYear;
    }

    public Book() {
    }

    public String getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(String publishingYear) {
        this.publishingYear = publishingYear;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Book book = (Book) object;
        return super.equals(book) &&
                Objects.equals(publishingYear, book.publishingYear);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(publishingYear);
    }
}
