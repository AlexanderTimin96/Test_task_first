package repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tandemservice.model.Player;
import ru.tandemservice.model.Points;
import ru.tandemservice.repository.leadersboard.InMemoryRepositoryLeadersBoard;
import ru.tandemservice.repository.leadersboard.RepositoryLeadersBoard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class InMemoryRepositoryLeadersTest {

    RepositoryLeadersBoard repositoryLeaders = InMemoryRepositoryLeadersBoard.getInstance();

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(new Player("test", "test@test.ru"), 0),
                Arguments.of(new Player("test2", "test2@test.ru"), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void setPointsTest_NotThrowException(Player player, int points) {
        Assertions.assertDoesNotThrow(() -> repositoryLeaders.setPoints(player, points));
    }


    @Test
    public void getLeaderboardTest() {
        repositoryLeaders.setPoints(new Player("Sergey", "test@test1.ru"), 4);
        repositoryLeaders.setPoints(new Player("Alex", "test@test2.ru"), 1);
        repositoryLeaders.setPoints(new Player("Oleg", "test@test3.ru"), 6);
        repositoryLeaders.setPoints(new Player("Anatoliy", "test@test4.ru"), 5);

        Map<Player, Points> result = repositoryLeaders.getLeaderBoard(3);


        Map<Player, Points> expected = new LinkedHashMap<>();
        expected.put(new Player("Oleg", "test@test3.ru"), new Points(6));
        expected.put(new Player("Anatoliy", "test@test4.ru"), new Points(5));
        expected.put(new Player("Sergey", "test@test1.ru"), new Points(4));
        Assertions.assertEquals(result.values().toString(), expected.values().toString());
    }
}
