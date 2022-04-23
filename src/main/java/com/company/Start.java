package com.company;

import com.company.firstMenu.CreateAccountOption;
import com.company.firstMenu.EnterAccountOption;
import com.company.firstMenu.Menu;
import com.company.firstMenu.UserActionInMenu;
import com.company.input.ConsoleInput;
import com.company.input.Input;
import com.company.input.ValidateInput;
import com.company.output.ConsoleOutput;
import com.company.output.Output;
import com.company.sql.SqlMenu;
import com.company.sql.SqlOptions;

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
            if (extracted(actions, select)) continue;
            UserActionInMenu action = actions.get(select);
            account = action.execute(input, memTracker);
            run1 = false;
        }
        while (run2) {
            this.shoOptions(options);
            int select = input.askInt("Select: ");
            if (extracted(actions, select)) continue;
            UserOption option = options.get(select);
            run2 = option.execute(input, ofa, account);
        }
    }

    private boolean extracted(List<UserActionInMenu> actions, int select) {
        boolean rsl = false;
        if (select < 0 || select >= actions.size()) {
            out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
            rsl = true;
        }
        return rsl;
    }

    private void showMenu(List<UserActionInMenu> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    private void shoOptions(List<UserOption> options) {
        out.println("Options:");
        for (int index = 0; index < options.size(); index++) {
            out.println(index + ". " + options.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (SqlMenu tracker = new SqlMenu(); SqlOptions tracker1 = new SqlOptions()) {
            tracker.init();
            List<UserActionInMenu> actions = List.of(
                    new CreateAccountOption(output),
                    new EnterAccountOption(output)
            );
            tracker1.init();
            List<UserOption> options = List.of(
                    new DepositOption(output),
                    new WithdrawOption(output),
                    new BuyCryptoOption(output),
                    new SellCryptoOption(output),
                    new ShowBalanceOption(output),
                    new ExitOption(output)
            );
            new Start(output).init(input, tracker,  actions, tracker1, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
