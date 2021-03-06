package com.company.firstMenu;

import com.company.Account;
import com.company.User;
import com.company.firstMenu.Menu;
import com.company.firstMenu.UserActionInMenu;
import com.company.input.Input;
import com.company.output.Output;

public class EnterAccountOption implements UserActionInMenu {
    private final Output out;

    public EnterAccountOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Enter Account";
    }

    @Override
    public Account execute(Input input, Menu menu) {
        out.println("=============Enter Account=============");
        String name = input.askString("Enter your name: ");
        int passport = input.askInt("Enter your passport number: ");
        String password = input.askString("Enter password: ");
        User user = new User(name, passport);
        return menu.enterAccount(user, password);
    }
}
