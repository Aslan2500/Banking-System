package com.company;

public interface Wallet {
    int getBalance();
    void addMoney(int amountToAdd);
    void takeMoney(int amountToWithdraw);
}
