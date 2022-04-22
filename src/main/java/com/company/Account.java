package com.company;

public class Account {
    private final User user;
    private final SafeWallet safeWallet = new SafeWallet();
    private final Wallet cryptoWallet = new CryptoWallet();
    private final int password;

    public Account(User user, int password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public SafeWallet getSafeWallet() {
        return safeWallet;
    }

    public Wallet getCryptoWallet() {
        return cryptoWallet;
    }

    public int getPassword() {
        return password;
    }
}
