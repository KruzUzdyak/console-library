package io.github.kruzuzdyak.console_lib.console.validator;

import java.net.URI;

public class EBookValidator extends BookValidator {

    public boolean validateUrl(String url) {
        try {
            URI.create(url);
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }
}
