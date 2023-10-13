package ru.tandemservice.command.commandImpl;

import ru.tandemservice.command.Command;
import ru.tandemservice.command.CommandName;
import ru.tandemservice.model.Player;
import ru.tandemservice.model.Points;
import ru.tandemservice.repository.leadersboard.InMemoryRepositoryLeadersBoard;
import ru.tandemservice.repository.leadersboard.RepositoryLeadersBoard;

import java.util.Map;

public class LeadersCommand implements Command {
    private final int NUMBER_OF_LEADERS = 5;
    private final RepositoryLeadersBoard repositoryLeadersBoard;

    public LeadersCommand() {
        this.repositoryLeadersBoard = InMemoryRepositoryLeadersBoard.getInstance();
    }

    @Override
    public void execute() {
        Map<Player, Points> leaderboard = repositoryLeadersBoard.getLeaderBoard(NUMBER_OF_LEADERS);
        System.out.println("Таблица лидеров:");
        leaderboard.forEach((player, points) -> System.out.println(player + " : " + points));
        System.out.println();
    }

    @Override
    public String getCommandName() {
        return CommandName.LEADERS.getCommandName();
    }
}
