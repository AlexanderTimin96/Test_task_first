package ru.tandemservice.repository.palindrome;

import ru.tandemservice.model.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryRepositoryPalindrome implements RepositoryPalindrome {

    private final Map<Player, Set<String>> enteredPalindromes;

    public InMemoryRepositoryPalindrome() {
        this.enteredPalindromes = new HashMap<>();
    }

    @Override
    public boolean isValidPalindrome(Player player, String palindrome) {
        if (!enteredPalindromes.containsKey(player)) {
            Set<String> palindromeStorage = new HashSet<>();
            palindromeStorage.add(palindrome);
            enteredPalindromes.put(player, palindromeStorage);
            return true;
        }

        Set<String> palindromeStorage = enteredPalindromes.get(player);
        if (!palindromeStorage.contains(palindrome)) {
            palindromeStorage.add(palindrome);
            return true;
        }
        return false;
    }
}
