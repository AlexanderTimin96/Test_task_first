package ru.tandemservice.checker;

import ru.tandemservice.model.Player;
import ru.tandemservice.repository.palindrome.RepositoryPalindrome;

public class PalindromeCheckerImpl implements PalindromeChecker {

    private final RepositoryPalindrome repositoryPalindrome;

    public PalindromeCheckerImpl(RepositoryPalindrome repositoryPalindrome) {
        this.repositoryPalindrome = repositoryPalindrome;
    }

    @Override
    public int getPoints(Player player, String str) {

        //(Проверка на null)
        if (str == null) {
            return 0;
        }

        //(Убираем пробелы между словами если они имеются)
        str = str.toLowerCase().replace(" ", "");

        //(Проверка, что строчка состояла не из одних пробелов)
        if (str.equals("")) {
            return 0;
        }

        StringBuilder stringBuilder = new StringBuilder().append(str);
        //(Переворачиваю строчку)
        String reversStr = stringBuilder.reverse().toString();

        //(Проверяю одинаковость строчек и считаю количество символов)
        if (str.equals(reversStr)) {
            //(Проверяю был ли введен уже такой палиндром данным игроком)
            if (repositoryPalindrome.isValidPalindrome(player, str)) {
                return str.length();
            }
        }
        return 0;
    }
}
