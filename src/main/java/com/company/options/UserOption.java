package com.company.options;

import com.company.Account;
import com.company.input.Input;

public interface UserOption {

    String name();

    boolean execute(Input input, OptionsForAccount options, Account account);
}
