package ru.tandemservice.repository.leaders;

import java.util.Map;

public interface RepositoryLeaders {
    void setPoints(String nickname, int points);

    Map<String, Integer> getLeaderboard(int limit);
}
