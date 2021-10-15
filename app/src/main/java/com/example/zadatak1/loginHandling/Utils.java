package com.example.zadatak1.loginHandling;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isUsernameValid(CharSequence username) {
        return username.length() > 0;
    }

    public static boolean isPasswordValid(CharSequence password) {
        return password.length() >= 6;
    }

    public static boolean isNameValid(CharSequence name) {
        return name.length() > 0;
    }

    public static boolean isNumberValid(CharSequence num) {
        return num.length() > 0;
    }
}

