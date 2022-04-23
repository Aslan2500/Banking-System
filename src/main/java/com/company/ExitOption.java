package com.company;

import com.company.input.Input;
import com.company.output.Output;

public class ExitOption implements UserOption {
    private final Output out;

    public ExitOption(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, OptionsForAccount options, Account account) {
        return false;
    }
}
