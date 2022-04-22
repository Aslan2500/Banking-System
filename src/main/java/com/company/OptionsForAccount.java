package com.company;

import java.util.List;

public interface OptionsForAccount {

    boolean depositMoney(int amountOfMoney, Account account);

    boolean withdrawMoney(int amountOfMoney, Account account);

    boolean buyCrypto(int amountOfMoney, Account account, double bitcoinPrice);

    boolean sellCrypto(double bitcoinPrice, double amountOfBitcoin, Account account);

    List<Integer> showBalance(Account account);
}
