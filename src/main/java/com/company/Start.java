package com.company;

import com.company.firstMenu.CreateAccountOption;
import com.company.firstMenu.EnterAccountOption;
import com.company.firstMenu.Menu;
import com.company.firstMenu.UserActionInMenu;
import com.company.input.ConsoleInput;
import com.company.input.Input;
import com.company.input.ValidateInput;
import com.company.options.*;
import com.company.output.ConsoleOutput;
import com.company.output.Output;
import com.company.firstMenu.SqlMenu;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

import java.util.List;

public class Start {
    private final Output out;

    public Start(Output out) {
        this.out = out;
    }

    public void init(Input input, Menu memTracker, List<UserActionInMenu> actions,
                     OptionsForAccount ofa, List<UserOption> options) {
        boolean run1 = true;
        boolean run2 = true;
        Account account = null;
        while (run1) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserActionInMenu action = actions.get(select);
            account = action.execute(input, memTracker);
            run1 = false;
        }
        if (account == null) {
            run2 = false;
        }
        while (run2) {
            this.showOptions(options);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= options.size()) {
                out.println("Wrong input, you can select: 0 .. " + (options.size() - 1));
                continue;
            }
            UserOption option = options.get(select);
            run2 = option.execute(input, ofa, account);
        }
    }

    private void showMenu(List<UserActionInMenu> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    private void showOptions(List<UserOption> options) {
        out.println("Options:");
        for (int index = 0; index < options.size(); index++) {
            out.println(index + ". " + options.get(index).name());
        }
    }

    public static void main(String[] args) {
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(new NullAppender());
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        SqlMenu tracker = new SqlMenu();
        SqlOptions tracker1 = new SqlOptions();
        List<UserActionInMenu> actions = List.of(
                new CreateAccountOption(output),
                new EnterAccountOption(output)
        );
        List<UserOption> options = List.of(
                new DepositOption(output),
                new WithdrawOption(output),
                new BuyCryptoOption(output),
                new SellCryptoOption(output),
                new ShowBalanceOption(output),
                new ExitOption(output)
        );
        new Start(output).init(input, tracker, actions, tracker1, options);
    }
}
