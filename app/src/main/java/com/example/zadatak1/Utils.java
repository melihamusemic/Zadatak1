package com.example.zadatak1;

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
}

