package io.github.kruzuzdyak.console_lib.entity;

public abstract class AbstractBook {

    protected String name;
    protected String author;

    public AbstractBook(String title, String author) {
        this.name = title;
        this.author = author;
    }

    public AbstractBook() {
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
}
