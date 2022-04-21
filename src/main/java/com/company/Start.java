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

import java.util.List;

public class Start {
    private final Output out;

    public Start(Output out) {
        this.out = out;
    }

    public void init(Input input, Menu memTracker, List<UserActionInMenu> actions) {
        boolean run1 = true;
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
    }

    private void showMenu(List<UserActionInMenu> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (SqlMenu tracker = new SqlMenu()) {
            tracker.init();
            List<UserActionInMenu> actions = List.of(
                    new CreateAccountOption(output),
                    new EnterAccountOption(output)
            );
            new Start(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
