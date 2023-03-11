package io.github.kruzuzdyak.console_lib.storage.impl;

import io.github.kruzuzdyak.console_lib.entity.EBook;
import io.github.kruzuzdyak.console_lib.storage.EBookStorage;

public class EBookStorageImpl extends AbstractStorage<EBook> implements EBookStorage {

    private static final String EBOOK_FILE_NAME = "ebooks.txt";
    private static final String EBOOK_PLACEHOLDER = "%s ||| %s ||| %s ||| %s\n";
    private static final String SEPARATOR = "\\s\\|{3}\\s";

    public EBookStorageImpl() {
        super(EBOOK_FILE_NAME);
    }

    @Override
    protected EBook convertToEntity(String ebookString) {
        String[] attributes = ebookString.split(SEPARATOR);
        return new EBook(attributes[0], attributes[1], attributes[2], attributes[3]);
    }

    @Override
    protected String convertToString(EBook eBook) {
        return String.format(EBOOK_PLACEHOLDER, eBook.getName(), eBook.getAuthor(), eBook.getPublishingYear(), eBook.getUrl());
    }
}
