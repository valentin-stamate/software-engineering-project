package com.bfourclass.euopendata.user.forms;

import com.bfourclass.euopendata.user.json.OwnerRegisterJSONRequest;
import com.bfourclass.euopendata.user.json.UserLoginJSON;
import com.bfourclass.euopendata.user.json.UserRegisterJSONRequest;

import java.util.regex.Pattern;

public abstract class FormValidator {
    private static final Pattern usernamePattern = Pattern.compile("(([_A-Za-z0-9]){4,15})");
    private static final Pattern emailPattern = Pattern.compile("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})");
    private static final Pattern passPattern = Pattern.compile("([_a-zA-Z0-9@#$%!]){6,66}");
    private static final Pattern linkPattern = Pattern.compile("(http|https)://(www).([a-z.]*)?(/[a-z1-9/]*)*\\??([&a-z1-9=]*)?");

    public static String isValidRegisterForm(UserRegisterJSONRequest registerForm) {
        // weird style of writing an if stmt
        if (registerForm.username == null
                || registerForm.email == null
                || registerForm.password == null
                || registerForm.profilePhotoLink == null
        ) return "Complete all fields";

        if (!FormValidator.isValidUsername(registerForm.username)) {
            return "Invalid username";
        }

        if (!FormValidator.isValidEmail(registerForm.email)) {
            return "Invalid email";
        }

        if (!FormValidator.isValidPassword(registerForm.password)) {
            return "Invalid password";
        }

        if (!FormValidator.isValidLink(registerForm.profilePhotoLink)) {
            return "Invalid email";
        }

        return null;
    }

    public static String isValidOwnerRegisterForm(OwnerRegisterJSONRequest registerForm) {
        // weird style of writing an if stmt
        if (registerForm.username == null
                || registerForm.email == null
                || registerForm.password == null
                || registerForm.profilePhotoLink == null
        ) return "Complete all fields";

        if (!FormValidator.isValidUsername(registerForm.username)) {
            return "Invalid username";
        }

        if (!FormValidator.isValidEmail(registerForm.email)) {
            return "Invalid email";
        }

        if (!FormValidator.isValidPassword(registerForm.password)) {
            return "Invalid password";
        }

        if (!FormValidator.isValidLink(registerForm.profilePhotoLink)) {
            return "Invalid email";
        }

        return null;
    }

    public static String isValidLoginForm(UserLoginJSON userLoginJSON) {

        if (!isValidUsername(userLoginJSON.login) && !isValidEmail(userLoginJSON.login)) {
            return "Invalid username or email";
        }

        if (!isValidPassword(userLoginJSON.password)) {
            return "Invalid password";
        }

        return null;
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
