package ru.tandemservice.command;

import java.util.Scanner;

public interface Command {
    void execute();

    String getCommandName();
}
