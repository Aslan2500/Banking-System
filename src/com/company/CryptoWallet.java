package com.company;

import java.util.HashMap;
import java.util.Map;

public class CryptoWallet implements Wallet {
    private int balance;
    private Map<String, Integer> crypto = new HashMap<>();

    @Override
    public void addMoney(int amountToAdd) {
        balance += amountToAdd;
        System.out.println("Money added!");
    }

    public void buyCrypto(int amountToSpend, String cryptoName) {
        balanceValidation(amountToSpend);
        if (crypto.containsKey(cryptoName)) {
            int a = crypto.get(cryptoName);
            a += amountToSpend;
            crypto.put(cryptoName, a);
        } else {
            crypto.put(cryptoName, amountToSpend);
        }
        balance -= amountToSpend;
    }

    @Override
    public void takeMoney(int amountToWithdraw) {
        balanceValidation(amountToWithdraw);
        balance -= amountToWithdraw;
        System.out.println("You now have " + balance + " rubles on your balance");
    }

    public void balanceValidation(int amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Not enough money on the balance");
        }
    }

    @Override
    public int getBalance() {
        System.out.println("You have " + balance + " rubles on your balance");
        return balance;
    }

    @Override
    public String toString() {
        return "Crypto Wallet: " +
                "Balance = " + balance +
                "; Tokens = " + crypto +
                '.';
    }
}
