package ru.tandemservice.command.commandImpl;

import ru.tandemservice.command.Command;
import ru.tandemservice.command.CommandName;
import ru.tandemservice.model.Player;
import ru.tandemservice.repository.players.InMemoryRepositoryPlayers;
import ru.tandemservice.repository.players.RepositoryPlayers;

import java.util.Scanner;

public class RegistryCommand implements Command {
    private final RepositoryPlayers repositoryPlayers;

    public RegistryCommand() {
        this.repositoryPlayers = InMemoryRepositoryPlayers.getInstance();
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        String nickname;
        while (true) {
            System.out.println("Введите никнейм:");
            nickname = scanner.nextLine();
            nickname = nickname.replace(" ", "");
            if (nickname.length() < 3 && repositoryPlayers.isRegistryNickname(nickname)) {
                System.out.println("Введенный никнем меньше 3 символов или занят!");
                continue;
            }
            break;
        }

        //Проверку на корректность email не делал
        String email;
        while (true) {
            System.out.println("Введите email:");
            email = scanner.nextLine();
            email = email.replace(" ", "");
            if (email.length() < 6) {
                System.out.println("Введенный имя меньше 6 символов!");
                continue;
            }
            break;
        }

        Player player = new Player(nickname, email);
        repositoryPlayers.registryPlayer(player);

        System.out.println("Вы успешно зарегистрировались с никнеймом: " + nickname);
    }


    @Override
    public String getCommandName() {
        return CommandName.REGISTRY.getCommandName();
    }
}
