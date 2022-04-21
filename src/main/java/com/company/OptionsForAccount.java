package com.company;

public interface OptionsForAccount {

    boolean depositMoney(int amountOfMoney);

    boolean withdrawMoney(int amountOfMoney);

    boolean buyCrypto(int amountOfMoney);

    boolean sellCrypto(int amountOfMoney);

    int showBalance();
}
