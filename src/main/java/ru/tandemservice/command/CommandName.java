package ru.tandemservice.command;

public enum CommandName {
    REGISTRY("/registry"),
    PLAY("/play"),
    LEADERS("/leaders"),
    UNKNOWN("unknownCommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
