package test.java.Poker;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.Poker.Poker;

@RunWith(Parameterized.class)
public class PokerTest {

    private Poker poker;
    private final String filePath;
    private final Integer expected;

    public PokerTest(String path, Integer expected) {
        this.filePath = path;
        this.expected = expected;
    }

    @Parameters // REVIEW dmarkowski - missing file test1.txt
    public static Collection<Object[]> testedHands() {
        return Arrays.asList(
                new Object[][]{ { "src\\test\\resources\\Poker\\poker.txt", 376 }, { "src\\test\\resources\\Poker\\test1.txt", 46 },
                });
    }

    @Test
    public void playerOneIsWinner() throws IOException {
        // given
        // when
        poker = new Poker(filePath);

        // then
        assertEquals(expected, poker.numberOfWinsFirstPlayer());
    }
}
