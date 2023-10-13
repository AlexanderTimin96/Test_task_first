package ru.tandemservice.repository.players;

import ru.tandemservice.model.Player;

import java.util.List;

public interface RepositoryPlayers {
    boolean isRegistryPlayer(String nickname);

    boolean registryPlayer(Player player);

    List<Player> getAllPlayers();

    List<Player> getPlayersWithoutActivity7Days();

    Player findPlayerByNickName(String nickname);

    void setActivity(String nickname);
}
