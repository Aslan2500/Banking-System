package com.company;

import java.util.List;

public interface OptionsForAccount {

    boolean depositMoney(double amountOfMoney, Account account);

    boolean withdrawMoney(double amountOfMoney, Account account);

    boolean buyCrypto(double amountOfMoney, Account account, double bitcoinPrice);

    boolean sellCrypto(double bitcoinPrice, double amountOfBitcoin, Account account);

    List<Integer> showBalance(Account account);
}
