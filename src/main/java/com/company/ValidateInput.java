package com.company;

//Шаблон Декоратор
public class ValidateInput extends ConsoleInput {
    private final Output output;
    private final Input input;

    public ValidateInput(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public String askString(String question) {
        return input.askString(question);
    }

    @Override
    public int askInt(String question) {
        boolean flag = true;
        int rsl = 0;
        do {
            try {
                rsl = input.askInt(question);
                flag = false;
            } catch (NumberFormatException e) {
                output.println("Please enter valid number!");
            }
        } while(flag);
        return rsl;
    }
}
