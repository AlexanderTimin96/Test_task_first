package ru.tandemservice.repository.leadersboard;

import ru.tandemservice.model.Player;
import ru.tandemservice.model.Points;

import java.util.Map;
import java.util.Set;

public interface RepositoryLeadersBoard {
    void setPoints(Player player, int points);

    Map<Player, Points> getLeaderBoard(int limit);
}
