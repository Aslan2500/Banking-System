package com.company;

public class Account {
    private final User user;
    private final int password;

    public Account(User user, int password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public int getPassword() {
        return password;
    }
}
