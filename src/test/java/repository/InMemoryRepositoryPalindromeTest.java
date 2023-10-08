package repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tandemservice.repository.palindrome.InMemoryRepositoryPalindrome;

import java.util.stream.Stream;

public class InMemoryRepositoryPalindromeTest {

    InMemoryRepositoryPalindrome repositoryPalindrome = new InMemoryRepositoryPalindrome();

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("Alex", "топот", true),
                Arguments.of("Sergey", "топот", true)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void isValidPalindromeTest_true(String nickname, String palindrome, boolean expected) {
        boolean result = repositoryPalindrome.isValidPalindrome(nickname, palindrome);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isValidPalindromeTest_false() {
        repositoryPalindrome.isValidPalindrome("alex", "топот");
        boolean result = repositoryPalindrome.isValidPalindrome("alex", "топот");
        Assertions.assertFalse(result);
    }
}
