package com.company;

import com.company.input.Input;
import com.company.output.Output;

public class DepositOption implements UserOption {
    private final Output out;

    public DepositOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Deposit Money";
    }

    @Override
    public boolean execute(Input input, OptionsForAccount options, Account account) {
        out.println("=============Deposit Money=============");
        double money = input.askDouble("Enter money: ");
        return options.depositMoney(money, account);
    }
}
