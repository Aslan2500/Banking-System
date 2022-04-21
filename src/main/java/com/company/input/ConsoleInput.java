package com.company.input;

import com.company.input.Input;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askString(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askString(question));
    }
}
