package ru.tandemservice.repository.leadersboard;

import ru.tandemservice.model.Player;
import ru.tandemservice.model.Points;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRepositoryLeadersBoard implements RepositoryLeadersBoard {

    private final Map<Player, Points> playersBoard;

    private static RepositoryLeadersBoard instance;

    //Сделал синглтоном потому что инстанс нужен в некоторых командах
    public static RepositoryLeadersBoard getInstance() {
        if (instance == null)
            instance = new InMemoryRepositoryLeadersBoard();
        return instance;
    }

    private InMemoryRepositoryLeadersBoard() {
        this.playersBoard = new HashMap<>();
    }

    @Override
    public void setPoints(Player player, int points) {
        if (!playersBoard.containsKey(player)) {
            playersBoard.put(player, new Points(points));
            return;
        }
        Points currPoints = playersBoard.get(player);
        currPoints.setPoints(currPoints.getPoints() + points);

        playersBoard.put(player, currPoints);
    }

    //возвращаем лидеров
    @Override
    public Map<Player, Points> getLeaderBoard(int limit) {
        return playersBoard.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue(Comparator.comparingInt(Points::getPoints))))
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }
}
