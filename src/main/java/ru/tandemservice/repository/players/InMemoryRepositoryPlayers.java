package ru.tandemservice.repository.players;

import ru.tandemservice.model.Player;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRepositoryPlayers implements RepositoryPlayers {

    //Обеспечивает уникальность игроков
    private final Map<String, Player> playerStorage;

    //Сделал синглтоном потому что инстанс нужен в некоторых командах
    private static RepositoryPlayers instance;

    public static RepositoryPlayers getInstance() {
        if (instance == null)
            instance = new InMemoryRepositoryPlayers();
        return instance;
    }

    private InMemoryRepositoryPlayers() {
        playerStorage = new HashMap<>();
    }

    @Override
    public boolean isRegistryNickname(String nickname) {
        return playerStorage.containsKey(nickname);
    }

    @Override
    public void registryPlayer(Player player) {
        if (!playerStorage.containsKey(player.getNickname())) {
            playerStorage.put(player.getNickname(), player);
        }
    }

    //возвращаем игроков допустим для массовых рассылок В ДАННОМ ПРОЕКТЕ НЕ ИСПОЛЬЗУЕТСЯ
    @Override
    public List<Player> getAllPlayers() {
        return new ArrayList<>(playerStorage.values());
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
        throw new RuntimeException("Запрашиваемого игрока не существует");
    }
}
