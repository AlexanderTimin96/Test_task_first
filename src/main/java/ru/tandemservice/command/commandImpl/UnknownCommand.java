package ru.tandemservice.command.commandImpl;

import ru.tandemservice.command.Command;
import ru.tandemservice.command.CommandName;

public class UnknownCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Ты ввел что то, чего я не знаю. Попробуй еще разок!");
    }

    @Override
    public String getCommandName() {
        return CommandName.UNKNOWN.getCommandName();
    }
}
