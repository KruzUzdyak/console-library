package io.github.kruzuzdyak.console_lib.console.validator;

import io.github.kruzuzdyak.console_lib.entity.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserValidator {

    private static final String NAME_PATTERN = "[\\w\\s-']+";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[\\w&&[^_]]{3,10}$";
    private static final String EMAIL_PATTERN = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Set<String> ROLES;

    static {
        ROLES = Arrays.stream(Role.values())
                .map(Role::toString)
                .collect(Collectors.toSet());
    }

    public boolean validateName(String name) {
        return name.matches(NAME_PATTERN);
    }

    public boolean validateName(String name, List<String> presentNames) {
        return name.matches(NAME_PATTERN) && !presentNames.contains(name);
    }

    public boolean validatePassword(String pass) {
        return pass.matches(PASSWORD_PATTERN);
    }

    public boolean validateEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public boolean validateRole(String role) {
        return ROLES.contains(role);
    }
}
