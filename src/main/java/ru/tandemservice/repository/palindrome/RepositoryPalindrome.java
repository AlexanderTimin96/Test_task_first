package ru.tandemservice.repository.palindrome;

import ru.tandemservice.model.Player;

public interface RepositoryPalindrome {
    boolean isValidPalindrome(Player player, String palindrome);
}
