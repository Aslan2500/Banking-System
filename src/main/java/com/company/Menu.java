package com.company;

public interface Menu {
    boolean createAccount(User user, int password);

    Account enterAccount(User user, int password);
}