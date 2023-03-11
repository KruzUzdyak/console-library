package io.github.kruzuzdyak.console_lib.entity;

import java.util.Objects;

public class Book {

    protected String name;
    protected String author;
    protected String publishingYear;

    public Book(String title, String author, String publishingYear) {
        this.name = title;
        this.author = author;
        this.publishingYear = publishingYear;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        Book that = (Book) object;
        return Objects.equals(name, that.name) &&
                Objects.equals(author, that.author) &&
                Objects.equals(publishingYear, that.publishingYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, publishingYear);
    }
}
