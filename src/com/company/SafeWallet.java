package com.company;

public class SafeWallet implements Wallet {
    private int balance;
    private final int depositPercentage = 5;

    @Override
    public int getBalance() {
        System.out.println("You have " + balance + " rubles on your balance");
        return balance;
    }

    @Override
    public void addMoney(int amountToAdd) {
        balance += amountToAdd;
    }

    @Override
    public void takeMoney(int amountToWithdraw) {
        balanceValidation(amountToWithdraw);
        balance -= amountToWithdraw;
        System.out.println("You now have " + balance + " rubles on your balance");
    }

    public void balanceValidation(int amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Not enough money on the account");
        }
    }

    @Override
    public void showBalance() {
        System.out.println("Your current balance - " + balance);
    }

    @Override
    public String toString() {
        return "Safe Wallet: " +
                "Balance = " + balance +
                '.';
    }
}
