package com.company.options;

import com.company.Account;
import com.company.input.Input;
import com.company.output.Output;
import com.company.parse.BitcoinPriceParse;

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
        double price = BitcoinPriceParse.getPrice();
        return options.buyCrypto(money, price, account);
    }
}
