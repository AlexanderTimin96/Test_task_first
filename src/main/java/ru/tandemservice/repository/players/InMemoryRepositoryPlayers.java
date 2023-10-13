package ru.tandemservice.repository.players;

import ru.tandemservice.model.Player;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRepositoryPlayers implements RepositoryPlayers {

    //Обеспечивает уникальность игроков
    private final Map<String, Player> playerStorage;

    public InMemoryRepositoryPlayers() {
        playerStorage = new HashMap<>();
    }

    @Override
    public boolean isRegistryPlayer(String nickname) {
        return playerStorage.containsKey(nickname);
    }

    @Override
    public boolean registryPlayer(Player player) {
        if (!playerStorage.containsKey(player.getNickname())) {
            playerStorage.put(player.getNickname(), player);
            return true;
        }
        return false;
    }

    //возвращаем игроков допустим для массовых рассылок В ДАННОМ ПРОЕКТЕ НЕ ИСПОЛЬЗУЕТСЯ
    @Override
    public List<Player> getAllPlayers() {
        return new ArrayList<Player>(playerStorage.values());
    }

    //возвращаем игроков, которые не проявляли активность последний 7 дней (допустим отправляем им на почту
    // письмо с напоминанием об игре) В ДАННОМ ПРОЕКТЕ НЕ ИСПОЛЬЗУЕТСЯ
    @Override
    public List<Player> getPlayersWithoutActivity7Days() {
        return playerStorage.values().stream()
                .filter(player -> player.getLastActivityDateTime().isBefore(LocalDateTime.now().minusDays(7L)))
                .collect(Collectors.toList());
    }

    @Override
    public Player findPlayerByNickName(String nickname) {
        if (playerStorage.containsKey(nickname)) {
            return playerStorage.get(nickname);
        }
        throw new IllformedLocaleException("Запрашиваемого игрока не существует");
    }


    //Устанавливаем последнюю активность
    @Override
    public void setActivity(String nickname) {
        if (playerStorage.containsKey(nickname)) {
            playerStorage.get(nickname).setLastActivityDateTime(LocalDateTime.now());
        }
        throw new IllformedLocaleException("Данного игрока не существует");
    }
}
