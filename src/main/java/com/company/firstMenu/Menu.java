package com.company.firstMenu;

import com.company.Account;
import com.company.User;

public interface Menu {
    Account createAccount(User user, int password);

    Account enterAccount(User user, int password);
}