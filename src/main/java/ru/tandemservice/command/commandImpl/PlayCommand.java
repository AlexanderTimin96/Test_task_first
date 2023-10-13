package ru.tandemservice.command.commandImpl;

import ru.tandemservice.checker.PalindromeChecker;
import ru.tandemservice.checker.PalindromeCheckerImpl;
import ru.tandemservice.command.Command;
import ru.tandemservice.command.CommandName;
import ru.tandemservice.model.Player;
import ru.tandemservice.repository.leadersboard.InMemoryRepositoryLeadersBoard;
import ru.tandemservice.repository.leadersboard.RepositoryLeadersBoard;
import ru.tandemservice.repository.palindrome.InMemoryRepositoryPalindrome;
import ru.tandemservice.repository.players.InMemoryRepositoryPlayers;
import ru.tandemservice.repository.players.RepositoryPlayers;

import java.util.Scanner;

public class PlayCommand implements Command {

    private final RepositoryPlayers repositoryPlayers;
    private final PalindromeChecker palindromeChecker;
    private final RepositoryLeadersBoard repositoryLeadersBoard;

    public PlayCommand() {
        this.repositoryPlayers = InMemoryRepositoryPlayers.getInstance();
        palindromeChecker = new PalindromeCheckerImpl(new InMemoryRepositoryPalindrome());
        this.repositoryLeadersBoard = InMemoryRepositoryLeadersBoard.getInstance();
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Начнем игру, для выхода в главное меню введите /end ");
            System.out.println("Введите <НИКНЕЙМ>: <ПАЛИНДРОМ>");

            String enteredStr = scanner.nextLine();

            if (enteredStr.equals("/end")) {
                break;
            }

            String[] path = enteredStr.split(":");
            if (path.length < 2) {
                System.out.println("Вы ввели что то некорректное. Пробуй еще!");
                continue;
            }
            String nickname = path[0];


            if (!repositoryPlayers.isRegistryNickname(nickname)) {
                System.out.println("Зарегистрированного пользователя с таким никнеймом нет!");
                continue;
            }

            Player player = repositoryPlayers.findPlayerByNickName(nickname);
            player.setLastActivityDateTime();
            int points = palindromeChecker.getPoints(player, path[1]);

            if (points == 0) {
                System.out.println("К сожалению это не палиндром, либо такая строка уже была использована");
                System.out.println();
                continue;
            }

            System.out.println("Это палиндром, очки за данную строку: " + points);
            repositoryLeadersBoard.setPoints(player, points);
        }
    }


    @Override
    public String getCommandName() {
        return CommandName.PLAY.getCommandName();
    }
}
