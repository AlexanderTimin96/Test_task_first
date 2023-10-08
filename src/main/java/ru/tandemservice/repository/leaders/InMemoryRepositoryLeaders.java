package ru.tandemservice.repository.leaders;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryRepositoryLeaders implements RepositoryLeaders {

    Map<String, Integer> resultTable;

    public InMemoryRepositoryLeaders() {
        this.resultTable = new HashMap<>();
    }

    @Override
    public void setPoints(String nickname, int points) {
        //(Проверяем есть ли такой никнейм уже в списке лидеров)
        if (!resultTable.containsKey(nickname)) {
            //(Добавляем игрока в список)
            resultTable.put(nickname, points);
            return;
        }

        //(Добавляем очки игроку)
        resultTable.put(nickname, resultTable.get(nickname) + points);
    }

    //Получаем отсортированную мапу с ограниченным определенным количеством и отсортированную
    @Override
    public Map<String, Integer> getLeaderboard(int limit) {
        Map<String, Integer> sortedMap = resultTable.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        return sortedMap;
    }
}
