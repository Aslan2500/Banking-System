package com.company;

public class Account {
    private final User user;
    private final int password;
    private int balance;

    public Account(User user, int password) {
        this.user = user;
        this.password = password;
    }

    public void getBalance() {
        System.out.println("You have " + balance + " rubles on your balance");
    }

    public void addMoney(int amount) {
        balance += amount;
    }

    public void takeMoney(int amount) {
        balanceValidation(amount);
        balance -= amount;
        System.out.println("You now have " + balance + " rubles on your balance");
    }

    public void passwordValidation(int pass) {
        if (pass != password) {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    public void balanceValidation(int amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Not enough money on the account");
        }
    }

    @Override
    public String toString() {
        return "Account: " +
                "User - { " + user +
                "; Balance = " + balance +
                '.';
    }
}
