package com.company.options;

import com.company.Account;
import com.company.input.Input;
import com.company.output.Output;
import com.company.parse.BitcoinPriceParse;

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
        double toSell = input.askDouble("How much Bitcoin would you like to sell: ");
        int price = (int) BitcoinPriceParse.getPrice();
        return options.sellCrypto(price, toSell, account);
    }
}
