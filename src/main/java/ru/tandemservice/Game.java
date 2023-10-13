package ru.tandemservice;

import ru.tandemservice.command.CommandContainer;

import java.util.Scanner;

public class Game {
    CommandContainer commandContainer;

    public Game() {
        this.commandContainer = new CommandContainer();
    }

    public void start() {

        String str;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Доступные команды:");
            System.out.println("Для регистрации нового игрока введите: /registry");
            System.out.println("Если вы зарегистрированы и хотите начать игру, введите /play");
            System.out.println("Для вывода таблицы лидеров введите /leaders");
            System.out.println("Для выхода из игры введите /end");

            str = scanner.nextLine();

            if (str.equals("end")) {
                System.out.println("Игра закончена!");
                break;
            }

            String commandIdentifier = str.toLowerCase().replace(" ", "");
            commandContainer.retrieveCommand(commandIdentifier).execute();
        }
    }
}


