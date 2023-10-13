package ru.tandemservice.repository.players;

import ru.tandemservice.model.Player;
import ru.tandemservice.model.Points;

import java.util.Map;
import java.util.Set;

public interface RepositoryPlayers {
    void setPoints(Player player, int points);

    Set<Player> getAllPlayers();

    Map<Player, Points> getPlayersWithoutActivity7Days();

    Map<Player, Points> getLeaderBoard(int limit);
}
