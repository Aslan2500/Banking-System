package com.company;

import com.company.input.Input;
import com.company.output.Output;

public class SellCryptoOption implements UserOption {
    private final Output out;

    public SellCryptoOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Sell Bitcoin";
    }

    @Override
    public boolean execute(Input input, OptionsForAccount options, Account account) {
        out.println("=============Sell Crypto=============");
        int toSell = input.askInt("How much Bitcoin would you like to sell: ");
        int price = input.askInt("Bitcoin price: ");
        return options.sellCrypto(price, toSell, account);
    }
}
