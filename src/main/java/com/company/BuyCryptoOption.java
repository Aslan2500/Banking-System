package com.company;

import com.company.input.Input;
import com.company.output.Output;

public class BuyCryptoOption implements UserOption {
    private final Output out;

    public BuyCryptoOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Buy Bitcoin";
    }

    @Override
    public boolean execute(Input input, OptionsForAccount options, Account account) {
        out.println("=============Buy Crypto=============");
        int money = input.askInt("How much money would you like to spend: ");
        int price = input.askInt("Bitcoin price: ");
        return options.buyCrypto(money, account, price);
    }
}
