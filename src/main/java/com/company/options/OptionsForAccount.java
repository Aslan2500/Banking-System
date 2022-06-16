package com.company.options;

import com.company.Account;

import java.util.List;

public interface OptionsForAccount {

    boolean depositMoney(int amountOfMoney, Account account);

    boolean withdrawMoney(int amountOfMoney, Account account);

    boolean buyCrypto(int amountOfMoney, double bitcoinPrice, Account account);

    boolean sellCrypto(int bitcoinPrice, double amountOfBitcoin, Account account);

    List<String> showBalance(Account account);
}
