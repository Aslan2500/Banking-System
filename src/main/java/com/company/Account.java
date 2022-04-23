package com.company;

public class Account {
    private final User user;
    private final String password;

    public Account(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
