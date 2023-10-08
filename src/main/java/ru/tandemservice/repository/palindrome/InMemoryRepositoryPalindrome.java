package ru.tandemservice.repository.palindrome;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryRepositoryPalindrome implements RepositoryPalindrome {

    Map<String, Set<String>> enteredPalindromes;

    public InMemoryRepositoryPalindrome() {
        this.enteredPalindromes = new HashMap<>();
    }

    @Override
    public boolean isValidPalindrome(String nickname, String palindrome) {
        if (!enteredPalindromes.containsKey(nickname)) {
            Set<String> palindromeStorage = new HashSet<>();
            palindromeStorage.add(palindrome);
            enteredPalindromes.put(nickname, palindromeStorage);
            return true;
        }

        Set<String> palindromeStorage = enteredPalindromes.get(nickname);
        if (!palindromeStorage.contains(palindrome)) {
            palindromeStorage.add(palindrome);
            return true;
        }
        return false;
    }
}
