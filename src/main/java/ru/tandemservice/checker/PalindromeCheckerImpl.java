package ru.tandemservice.checker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PalindromeCheckerImpl implements PalindromeChecker {

    Map<String, Set<String>> enteredPalindrome = new HashMap<>();

    public PalindromeCheckerImpl() {
        this.enteredPalindrome = new HashMap<>();
    }

    @Override
    public int getPoints(String nickname, String str) {

        //(Проверка на null)
        if (str == null) {
            return 0;
        }

        //(Проверка, что строчка состоит не из одних пробелов)
        str = str.toLowerCase().trim();
        if (str.equals("")) {
            return 0;
        }

        //(Убираем пробелы между словами если они имеются)
        String[] words = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            word = word.trim();
            if (word.equals("")) {
                continue;
            }
            stringBuilder.append(word);
        }

        //(Получаю строчку без пробелов)
        String enteredStr = stringBuilder.toString();
        //(Переворачиваю строчку)
        String reversStr = stringBuilder.reverse().toString();

        //(Проверяю одинаковость строчек и считаю количество символов)
        if (enteredStr.equals(reversStr)) {
            //(Проверяю был ли введен уже такой палиндром данным игроком)
            if (!enteredPalindrome.containsKey(nickname)) {
                Set<String> palindrome = new HashSet<>();
                palindrome.add(enteredStr);
                enteredPalindrome.put(nickname, palindrome);
            } else {
                Set<String> palindrome = enteredPalindrome.get(nickname);
                if (palindrome.contains(enteredStr) || palindrome.contains(reversStr)) {
                    return 0;
                }
            }

            return enteredStr.length();
        }
        return 0;
    }
}
