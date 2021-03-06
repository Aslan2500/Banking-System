package com.company.options;

import com.company.Account;
import com.company.input.Input;
import com.company.output.Output;

public class WithdrawOption implements UserOption {
    private final Output out;

    public WithdrawOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Withdraw Money";
    }

    @Override
    public boolean execute(Input input, OptionsForAccount options, Account account) {
        out.println("=============Withdraw Money=============");
        int money = input.askInt("How much money would you like to withdraw: ");
        return options.withdrawMoney(money, account);
    }
}
