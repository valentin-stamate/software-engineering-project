package com.bfourclass.euopendata.user.forms;

import com.bfourclass.euopendata.user.json.UserLoginJSONRequest;
import com.bfourclass.euopendata.user.json.UserRegisterJSONRequest;

import java.util.regex.Pattern;

public abstract class FormValidator {
    private static final Pattern usernamePattern = Pattern.compile("(([_A-Za-z0-9]){4,15})");
    private static final Pattern emailPattern = Pattern.compile("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})");
    private static final Pattern passPattern = Pattern.compile("([_a-zA-Z@#$%!]){6,66}");
    private static final Pattern linkPattern = Pattern.compile("(http|https)://(www).([a-z.]*)?(/[a-z1-9/]*)*\\??([&a-z1-9=]*)?");

    public static boolean isValidRegisterForm(UserRegisterJSONRequest registerForm) {
        // weird style of writing an if stmt
        if (registerForm.username == null
            || registerForm.email == null
            || registerForm.password == null
            || registerForm.profilePhotoLink == null
        ) return false;

        return FormValidator.isValidUsername(registerForm.username)
                && FormValidator.isValidEmail(registerForm.email)
                && FormValidator.isValidPassword(registerForm.password)
                && FormValidator.isValidLink(registerForm.profilePhotoLink);
    }

    public static boolean isValidLoginForm(UserLoginJSONRequest userLoginJSONRequest) {
        return isValidUsername(userLoginJSONRequest.username)
                && isValidPassword(userLoginJSONRequest.password);
    }

    public static boolean isValidUsername(String username) {
        return usernamePattern.matcher(username).matches();
    }

    public static boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return passPattern.matcher(password).matches();
    }

    public static boolean isValidLink(String link) {
        /* TODO */
//        return linkPattern.matcher(link).matches();
        return true;
    }
}
