package com.company.firstMenu;

import com.company.Account;
import com.company.User;

public interface Menu {
    Account createAccount(User user, String password);

    Account enterAccount(User user, String password);
}