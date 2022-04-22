package com.company.firstMenu;

import com.company.Account;
import com.company.input.Input;

public interface UserActionInMenu {

    String name();

    Account execute(Input input, Menu menu);
}
