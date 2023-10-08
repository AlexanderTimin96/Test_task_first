package repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tandemservice.repository.leaders.InMemoryRepositoryLeaders;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class InMemoryRepositoryLeadersTest {

    InMemoryRepositoryLeaders repositoryLeaders = new InMemoryRepositoryLeaders();

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("Alex", 1),
                Arguments.of("Roma", 2),
                Arguments.of("Katya", 3),
                Arguments.of("Sergey", 4),
                Arguments.of("Anatoliy", 5),
                Arguments.of("Oleg", 6)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void setPointsTest_NotThrowException(String nickname, int points) {
        Assertions.assertDoesNotThrow(() -> repositoryLeaders.setPoints(nickname, points));
    }


    @Test
    public void getLeaderboardTest() {
        repositoryLeaders.setPoints("Sergey", 4);
        repositoryLeaders.setPoints("Alex", 1);
        repositoryLeaders.setPoints("Oleg", 6);
        repositoryLeaders.setPoints("Anatoliy", 5);

        Map<String, Integer> result = repositoryLeaders.getLeaderboard(3);


        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("Oleg", 6);
        expected.put("Anatoliy", 5);
        expected.put("Sergey", 4);
        Assertions.assertEquals(result, expected);
    }
}
