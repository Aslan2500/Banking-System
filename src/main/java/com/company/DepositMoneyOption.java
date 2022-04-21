package com.company;

import com.company.output.Output;

public class DepositMoneyOption {

    private final Output out;
    private Account account;

    public DepositMoneyOption(Output out, Account account) {
        this.out = out;
        this.account = account;
    }

    public String name() {
        return "Deposit money";
    }

//    @Override
//    public boolean execute(Input input, OptionsForAccount ofa) {
//        out.println("=============Deposit Money=============");
//        int amount = input.askInt("Insert your money: ");
//        account.getCryptoWallet().addMoney(amount);
//        ofa.depositMoney(amount);
//        return true;
//    }
}
