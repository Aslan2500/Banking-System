package com.company;

public class Account {
    private final User user;
    private final CryptoWallet cryptoWallet = new CryptoWallet();
    private final SafeWallet safeWallet = new SafeWallet();
    private final int password;

    public Account(User user, int password) {
        this.user = user;
        this.password = password;
    }

    public SafeWallet getSafeWallet() {
        return safeWallet;
    }

    public CryptoWallet getCryptoWallet() {
        return cryptoWallet;
    }

    public int getPassword() {
        return password;
    }
}
