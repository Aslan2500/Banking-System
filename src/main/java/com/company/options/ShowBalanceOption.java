package com.company.options;

import com.company.Account;
import com.company.input.Input;
import com.company.output.Output;

import java.util.List;

public class ShowBalanceOption implements UserOption {
    private final Output out;

    public ShowBalanceOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show Balance";
    }

    @Override
    public boolean execute(Input input, OptionsForAccount options, Account account) {
        out.println("=============Show Balance=============");
        List<String> list = options.showBalance(account);
        out.println("Money on balance " + list.get(0));
        out.println("Bitcoin on balance " + list.get(1));
        return true;
    }
}
