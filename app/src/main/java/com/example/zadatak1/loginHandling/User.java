package com.example.zadatak1.loginHandling;

public class User {
    private String _username = "";
    private String _password = "";

    public User() {
    }

    public User(String username, String password) {
        _username = username;
        _password = password;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }
}
