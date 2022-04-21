package com.company.firstMenu;

import com.company.Account;
import com.company.User;
import com.company.firstMenu.Menu;
import com.company.firstMenu.UserActionInMenu;
import com.company.input.Input;
import com.company.output.Output;

public class CreateAccountOption implements UserActionInMenu {
    private final Output out;

    public CreateAccountOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create Account";
    }

    @Override
    public Account execute(Input input, Menu menu) {
        out.println("=============Create new Account=============");
        String name = input.askString("Enter your name: ");
        int id = input.askInt("Enter your ID: ");
        int password = input.askInt("Create password: ");
        User user = new User(name, id);
        return menu.createAccount(user, password);
    }
}
