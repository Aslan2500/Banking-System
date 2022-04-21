package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private static final Map<Integer, Account> map = new HashMap<>();

    public void createAccount(User user, int password) {
        if (map.containsKey(user.getId())) {
            throw new IllegalArgumentException("You already have tan account");
        }
        Account account = new Account(user, password);
        map.put(user.getId(), account);
        System.out.println("Thank you for joining our bank!");
    }

    public void deleteAccount(int id, int pass) {
        userValidation(id);
        passwordValidation(id, pass);
        Scanner sc = new Scanner(System.in);
        System.out.println("Could you please write why you decided to delete your account?\n" +
                "This would help us to develop our services");
        sc.nextLine();
        map.remove(id, map.get(id));
        System.out.println("Bye!");
    }

    public Account myAccount(int id, int pass) {
        userValidation(id);
        passwordValidation(id, pass);
        return map.get(id);
    }

    public void userValidation(int id) {
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException("No user with such id");
        }
    }

    public void passwordValidation(int id, int pass) {
        Account ac = map.get(id);
        if (ac.getPassword() != pass) {
            throw new IllegalArgumentException("Wrong password");
        }
    }
}
