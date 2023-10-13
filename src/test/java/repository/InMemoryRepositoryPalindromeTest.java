package repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tandemservice.model.Player;
import ru.tandemservice.repository.palindrome.InMemoryRepositoryPalindrome;

import java.util.stream.Stream;

public class InMemoryRepositoryPalindromeTest {

    InMemoryRepositoryPalindrome repositoryPalindrome = new InMemoryRepositoryPalindrome();

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(new Player("Alex", "test@test.ru"), "топот", true),
                Arguments.of("Sergey", "топот", true)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void isValidPalindromeTest_true(Player player, String palindrome, boolean expected) {
        boolean result = repositoryPalindrome.isValidPalindrome(player, palindrome);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isValidPalindromeTest_false() {
        repositoryPalindrome.isValidPalindrome(new Player("Alex", "test@test.ru"), "топот");
        boolean result = repositoryPalindrome.isValidPalindrome(new Player("Alex", "test@test.ru"), "топот");
        Assertions.assertFalse(result);
    }
}
