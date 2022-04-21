package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        System.out.print("Press 'Enter' to start");
        sc.nextLine();
        int x;
        do {
            showMenu();
            x = sc.nextInt();
            switch (x) {
                case 1 -> {
                    System.out.print("Please, enter your name: " + sc.nextLine());
                    String name = sc.nextLine();
                    int[] arr = nameAndId();
                    User user = new User(name, arr[0]);
                    bank.createAccount(user, arr[1]);
                }
                case 2 -> {
                    int[] arr = nameAndId();
                    Account acc = bank.myAccount(arr[0], arr[1]);
//                    CryptoWallet cryptoWallet = acc.getCryptoWallet();
//                    cryptoWallet.getBalance();
//                    System.out.print("How much would you like to spend? ");
//                    int am = sc.nextInt();
//                    String rsl = chooseCrypto();
//                    cryptoWallet.buyCrypto(am, rsl);
                }
                case 3 -> {
                    int[] arr = nameAndId();
                    bank.deleteAccount(arr[0], arr[1]);
                }
                case 4 -> {
                    int[] arr = nameAndId();
                    Account account1 = bank.myAccount(arr[0], arr[1]);
                    System.out.print("How much money would you like to add?");
                    int amount = sc.nextInt();
                    int c;
                    do {
                        walletChooseMenu();
                        c = sc.nextInt();
                        switch(c) {
                            case 1 -> {
                                account1.getCryptoWallet().addMoney(amount);
                                c = 3;
                            }
                            case 2 -> {
                                account1.getSafeWallet().addMoney(amount);
                                c = 3;
                            }
                            default -> System.out.println("Enter valid number");
                        }
                    } while (c != 3);
                }
                case 5 -> {
                    int[] arr = nameAndId();
                    Account account1 = bank.myAccount(arr[0], arr[1]);
                    System.out.print("How much money would you like to withdraw?");
                    int amount = sc.nextInt();
                    int c;
                    do {
                        walletChooseMenu();
                        c = sc.nextInt();
                        switch(c) {
                            case 1 -> {
                                account1.getCryptoWallet().takeMoney(amount);
                                System.out.println("You can take your money");
                                c = 3;
                            }
                            case 2 -> {
                                account1.getSafeWallet().takeMoney(amount);
                                System.out.println("You can take your money");
                                c = 3;
                            }
                            default -> System.out.println("Enter valid number");
                        }
                    } while (c != 3);
                }
                case 6 -> {
                    int[] arr = nameAndId();
                    Account account5 = bank.myAccount(arr[0], arr[1]);
                    int c;
                    do {
                        walletChooseMenu();
                        c = sc.nextInt();
                        switch(c) {
                            case 1 -> {
                                account5.getCryptoWallet().getBalance();
                            }
                            case 2 -> {
                                account5.getSafeWallet().getBalance();
                            }
                            default -> System.out.println("Enter valid number");
                        }
                    } while (c != 3);
                }
                default -> System.out.println("Please, enter valid number");
            }
        } while (x != 7);
        System.out.println("\nThanks for visiting");
    }

    public static void showMenu() {
        System.out.println("==========================");
        System.out.println("1. Create Account");
        System.out.println("2. Buy crypto");
        System.out.println("3. Delete Account");
        System.out.println("4. Deposit money");
        System.out.println("5. Withdraw money");
        System.out.println("6. Show balance");
        System.out.println("7. Exit");
        System.out.println("==========================");
        System.out.println();
    }

    public static String chooseCrypto() {
        Scanner sc = new Scanner(System.in);
        int x;
        String rsl = "";
        do {
            showCryptoList();
            x = sc.nextInt();
            switch (x) {
                case 1 -> {
                    rsl = "BTC";
                    x = 6;
                }
                case 2 -> {
                    rsl = "ETH";
                    x = 6;
                }
                case 3 -> {
                    rsl = "NEAR";
                    x = 6;
                }
                case 4 -> {
                    rsl = "SOL";
                    x = 6;
                }
                case 5 -> {
                    rsl = "ADA";
                    x = 6;
                }
                default -> System.out.println("Please, type valid number");
            }
        } while (x != 6);
        return rsl;
    }

    public static void showCryptoList() {
        System.out.println("Please, choose which crypto you want to buy:");
        System.out.println("1. BTC");
        System.out.println("2. ETH");
        System.out.println("3. NEAR");
        System.out.println("4. SOL");
        System.out.println("5. ADA");
        System.out.println("6. If you want to exit");
    }

    public static int[] nameAndId() {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[2];
        System.out.println("Please enter your id and password:");
        System.out.print("id: ");
        arr[0] = sc.nextInt();
        System.out.print("password: ");
        arr[1] = sc.nextInt();
        return arr;
    }

    public static void walletChooseMenu() {
        System.out.println("==========================");
        System.out.println("Choose wallet:");
        System.out.println("1. Crypto wallet");
        System.out.println("2. Safe wallet");
        System.out.println("3. Exit");
        System.out.println("==========================");
    }
}
