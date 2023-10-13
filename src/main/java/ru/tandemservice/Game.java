package ru.tandemservice;

import java.util.Scanner;

public class Game {

    private final int NUMBER_OF_LEADERS = 5;

    public void start() {

        //Использую try с ресурсами чтобы сканер закрылся
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("доступные команды:");
                System.out.println("Для регистрации нового игрока введите: /registry");
                System.out.println("Если вы зарегистрированы введите /start");
                System.out.println("Для вывода таблицы лидеров введите /leaders");
                System.out.println("Для выхода из игры введите /end");

                String str = scanner.nextLine();

                if (str.equals("end")) {
                    System.out.println("Игра закончена!");
                    break;
                }

                


            }
        }
    }
}
