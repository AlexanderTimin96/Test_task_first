package ru.tandemservice.repository.players;

import ru.tandemservice.model.Player;

import java.util.List;

public interface RepositoryPlayers {
    boolean isRegistryNickname(String nickname);

    void registryPlayer(Player player);

    List<Player> getAllPlayers();

    List<Player> getPlayersWithoutActivity7Days();

    Player findPlayerByNickName(String nickname);
}
