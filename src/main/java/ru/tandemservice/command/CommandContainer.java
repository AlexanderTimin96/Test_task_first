package ru.tandemservice.command;

import ru.tandemservice.command.commandImpl.LeadersCommand;
import ru.tandemservice.command.commandImpl.PlayCommand;
import ru.tandemservice.command.commandImpl.RegistryCommand;
import ru.tandemservice.command.commandImpl.UnknownCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, Command> commands;

    private final Command play;
    private final Command unknownCommand;
    private final Command registry;

    private final Command leaders;

    public CommandContainer() {
        this.commands = new HashMap<>();

        play = new PlayCommand();
        unknownCommand = new UnknownCommand();
        registry = new RegistryCommand();
        leaders = new LeadersCommand();


        commands.put(unknownCommand.getCommandName(), unknownCommand);
        commands.put(play.getCommandName(), play);
        commands.put(registry.getCommandName(), registry);
        commands.put(leaders.getCommandName(), leaders);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commands.getOrDefault(commandIdentifier, unknownCommand);
    }
}
