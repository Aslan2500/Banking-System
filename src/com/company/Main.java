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
                    bank.deleteAccount(arr[0], arr[1]);
                }
                case 3 -> {
                    int[] arr = nameAndId();
                    Account account1 = bank.myAccount(arr[0], arr[1]);
                    System.out.print("How much money would you like to add? ");
                    int amount = sc.nextInt();
                    account1.addMoney(amount);
                    System.out.println("Money added");
                }
                case 4 -> {
                    int[] arr = nameAndId();
                    Account account2 = bank.myAccount(arr[0], arr[1]);
                    System.out.println("How much money would you like to withdraw? ");
                    int amount2 = sc.nextInt();
                    account2.takeMoney(amount2);
                    System.out.println("Please, take your money");
                }
                case 5 -> {
                    int[] arr = nameAndId();
                    Account account5 = bank.myAccount(arr[0], arr[1]);
                    account5.getBalance();
                }
                case 6 -> x = 6;
                default -> System.out.println("Please, enter valid number");
            }
        } while (x != 6);
        System.out.println("\nThanks for visiting");
    }

    public static void showMenu() {
        System.out.println("==========================");
        System.out.println("1. Create Account");
        System.out.println("2. Delete Account");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Show balance");
        System.out.println("6. Exit");
        System.out.println("==========================");
        System.out.println();
    }

    public static int[] nameAndId() {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[2];
        System.out.println("Please enter your id and password");
        System.out.print("id: ");
        arr[0] = sc.nextInt();
        System.out.print("password: ");
        arr[1] = sc.nextInt();
        return arr;
    }
}
