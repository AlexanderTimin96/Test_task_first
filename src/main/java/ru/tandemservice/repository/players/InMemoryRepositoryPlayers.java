package ru.tandemservice.repository.players;

import ru.tandemservice.model.Player;
import ru.tandemservice.model.Points;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRepositoryPlayers implements RepositoryPlayers {

    Map<Player, Points> playersBoard;

    public InMemoryRepositoryPlayers() {
        this.playersBoard = new HashMap<>();
    }

    @Override
    public void setPoints(Player player, int points) {

        Points currPoints = playersBoard.get(player);
        currPoints.setPoints(currPoints.getPoints() + points);

        playersBoard.put(player, currPoints);
    }

    //возвращаем игроков допустим для массовых рассылок
    @Override
    public Set<Player> getAllPlayers() {
        return playersBoard.keySet();
    };

    //возвращаем игроков, которые не проявляли активность последний 7 дней (допустим отправляем им на почту
    // письмо с напоминанием об игре, или что они потеряли лидерство и тд)
    @Override
    public Map<Player, Points> getPlayersWithoutActivity7Days() {
        return playersBoard.entrySet().stream()
                .filter(entry -> entry.getKey()
                        .getLastActivityDateTime().isAfter(LocalDateTime.now().minusDays(7L)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        HashMap::new
                ));
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
