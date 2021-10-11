package com.example.zadatak1;

import java.util.regex.Pattern;

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

