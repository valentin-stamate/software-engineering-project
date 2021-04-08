package com.bfourclass.euopendata.user.forms;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class FormValidator {
    private final Pattern usernamePattern = Pattern.compile("(([_A-Za-z0-9]){4,15})");
    private final Pattern emailPattern = Pattern.compile("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})");
    private final Pattern passPattern = Pattern.compile("([_a-zA-Z@#$%!]).{6,66}");
    private final Pattern linkPattern = Pattern.compile("(http|https)://(www).([a-z.]*)?(/[a-z1-9/]*)*\\??([&a-z1-9=]*)?");

    public boolean isValidRegisterForm(UserRegisterForm registerForm) {

        return isValidUsername(registerForm.getUsername())
                && isValidEmail(registerForm.getEmail())
                && isValidPassword(registerForm.getPassword())
                ; // TODO maybe also display name and image url
    }

    public boolean isValidUsername(String username) {
        return usernamePattern.matcher(username).matches();
    }

    public boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        return passPattern.matcher(password).matches();
    }

    public boolean isValidLink(String link) {
        return linkPattern.matcher(link).matches();
    }

    public boolean isValidLoginForm(UserLoginForm userLoginForm) {
        return isValidUsername(userLoginForm.getUsername())
                && isValidPassword(userLoginForm.getPassword());
    }
}
