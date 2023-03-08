package io.github.kruzuzdyak.console_lib.entity;

import java.util.Objects;

public class EBook extends AbstractBook{

    private String url;

    public EBook(String title, String author, String url) {
        super(title, author);
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
        return super.hashCode() + Objects.hash(url);
    }
}
