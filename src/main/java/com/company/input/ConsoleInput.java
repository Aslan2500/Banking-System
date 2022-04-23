package com.company.input;

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

    @Override
    public double askDouble(String question) {
        return Double.parseDouble(askString(question));
    }
}
