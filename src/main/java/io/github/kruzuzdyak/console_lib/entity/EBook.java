package io.github.kruzuzdyak.console_lib.entity;

import java.util.Objects;

public class EBook extends Book {

    private String url;

    public EBook(String title, String author, String publishingYear, String url) {
        super(title, author, publishingYear);
        this.url = url;
    }

    public EBook() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        EBook eBook = (EBook) object;
        return super.equals(eBook) &&
                Objects.equals(url, eBook.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, publishingYear, url);
    }

    @Override
    public String toString() {
        return "EBook{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishingYear='" + publishingYear + '\'' +
                '}';
    }
}
