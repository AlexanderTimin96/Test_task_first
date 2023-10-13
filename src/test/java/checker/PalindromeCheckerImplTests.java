package checker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.tandemservice.checker.PalindromeCheckerImpl;
import ru.tandemservice.model.Player;
import ru.tandemservice.repository.palindrome.RepositoryPalindrome;

import java.util.stream.Stream;

public class PalindromeCheckerImplTests {

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void getPoints_ParameterizedTest(String nickname, String palindrome, int expected) {
        RepositoryPalindrome repositoryPalindrome = Mockito.mock(RepositoryPalindrome.class);
        Mockito.when(repositoryPalindrome.isValidPalindrome(Mockito.any(), Mockito.any())).thenReturn(true);

        PalindromeCheckerImpl palindromeChecker = new PalindromeCheckerImpl(repositoryPalindrome);

        int result = palindromeChecker.getPoints(new Player("test", "test@test.ru"), palindrome);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("nickname", null, 0),
                Arguments.of("nickname", "    ", 0),
                Arguments.of("nickname", "test", 0),
                Arguments.of("nickname", " т о  по т   ", 5),
                Arguments.of("nickname", "топот  ", 5),
                Arguments.of("nickname", "а роза упала на лапу Азора", 21)
        );
    }
}
