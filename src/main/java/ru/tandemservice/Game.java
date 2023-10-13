package ru.tandemservice;

import ru.tandemservice.checker.PalindromeChecker;
import ru.tandemservice.checker.PalindromeCheckerImpl;
import ru.tandemservice.repository.players.InMemoryRepositoryPlayers;
import ru.tandemservice.repository.players.RepositoryPlayers;
import ru.tandemservice.repository.palindrome.InMemoryRepositoryPalindrome;

import java.util.Map;
import java.util.Scanner;

public class Game {
    // здесь я бы использовал DI спринга, чтобы классы были независимы друг от друга, но по заданию
    // нельзя использовать Frameworks
    private final PalindromeChecker palindromeChecker;
    //Если в дальнейшем будет использована БД, то просто меняем имплементацию интерфейса в конструкторе
    private final RepositoryPlayers repository;


    public Game() {
        this.palindromeChecker = new PalindromeCheckerImpl(new InMemoryRepositoryPalindrome());
        this.repository = new InMemoryRepositoryPlayers();
    }

    int NUMBER_LEADERS = 5;

    public void start() {

        //Использую try с ресурсами чтобы сканер закрылся
        try (Scanner scanner = new Scanner(System.in)) {
            //Открываем бесконечный цикл
            while (true) {
                System.out.println("Введите номер игрока и строку палиндром, вида: \"<НИКНЕЙМ>: <СТРОКА ПАЛИНДРОМ>\" " +
                        "(Никнейм должен состоять из 3 и более символов)");
                System.out.println("Введите end, для окончания игры");
                System.out.println("Введите leaders, для вывода таблицы лидеров");

                //(Считываем веденную строчку)
                String str = scanner.nextLine();

                //(проверка на выход из игры)
                if (str.equals("end")) {
                    System.out.println("Игра закончена!");
                    printLeaderboard(NUMBER_LEADERS);
                    break;
                }

                if (str.equals("leaders")) {
                    printLeaderboard(NUMBER_LEADERS);
                    continue;
                }

                //(Проверяем ник игрока)
                String[] words = str.split(":");
                if (words.length < 2 || words[0].trim().length() < 3) {
                    System.out.println("Введены не корректные данный! Пробуй снова!");
                    System.out.println();
                    continue;
                }

                //(Находим ник игрока)
                String nickname = words[0];
                nickname = nickname.trim();

                //(Преобразовываем обратно в строчку для проверки(вдруг в строчке встречается символ ":" не один раз) на палиндром)
                StringBuilder enteredStr = new StringBuilder();
                for (int i = 1; i < words.length; i++) {
                    enteredStr.append(words[i]);
                }

                //(Получаем очки палиндрома(количество символов))
                int points = palindromeChecker.getPoints(nickname, enteredStr.toString());

                if (points == 0) {
                    System.out.println("К сожалению это не палиндром, либо такая строка уже была использована");
                    System.out.println();
                    continue;
                }

                System.out.println("Это палиндром, очки за данную строку: " + points);
                repository.setPoints(nickname, points);
            }
        }
    }

    private void printLeaderboard(int limit) {
        Map<String, Integer> leaderboard = repository.getLeaderBoard(limit);
        System.out.println("Таблица лидеров:");
        leaderboard.forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println();
    }
}
