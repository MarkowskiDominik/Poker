package Poker;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Poker.Hand;
import Poker.Parser;

@RunWith(Parameterized.class)
public class HandTest {

    enum Type {
        GET_VALUE, COMPARE
    };

    private List<Hand> playersHands;
    private Parser parser;
    private final String roundNotation;
    private final Integer expectedHandValue;
    private final Type type;

    @Before
    public void initializeParser() {
        parser = new Parser();
    }

    @After
    public void finalizeParser() {
        playersHands = null;
        parser = null;
    }

    public HandTest(String roundNotation, Integer expectedHandValue, Type type) {
        this.roundNotation = roundNotation;
        this.expectedHandValue = expectedHandValue;
        this.type = type;
    }

    @Parameters
    public static Collection<Object[]> testedHands() {
        return Arrays.asList(new Object[][]{ { "9S QC AC JC TC 2D 3D 4D 5D 6D", 0, Type.GET_VALUE }, // High Card
                { "KC QC KS JC TC 2D 3D 4D 5D 6D", 1, Type.GET_VALUE }, // One Pair
                { "KC QC AC KS QS 2D 3D 4D 5D 6D", 2, Type.GET_VALUE }, // Two Pairs
                { "KC KS AC JC KD 2D 3D 4D 5D 6D", 3, Type.GET_VALUE }, // Three of a Kind
                { "KS QC AC JC TC 2D 3D 4D 5D 6D", 4, Type.GET_VALUE }, // Straight
                { "9C QC AC JC TC 2D 3D 4D 5D 6D", 5, Type.GET_VALUE }, // Flush
                { "KC QC KS QS KH 2D 3D 4D 5D 6D", 6, Type.GET_VALUE }, // Full House
                { "KC KS AC KD KH 2D 3D 4D 5D 6D", 7, Type.GET_VALUE }, // Four of a Kind
                { "KC QC AC JC TC 2D 3D 4D 5D 6D", 8, Type.GET_VALUE }, // Straight Flush
                { "2C 3C 4C 5C 6C 2D 3D 4D 5D 7D", 1, Type.COMPARE }, // Straight Flush VS. Flush
                { "2C 2D 4C 4D 6C 2D 3D 4D 5D 7S", 1, Type.COMPARE }, // Two Pairs VS. High Card
                { "2C 2D 4C 4D 2S 2D 3D 4D 5D 6S", 1, Type.COMPARE }, // Full House VS. Straight
                { "2C 3C 4C 5C 6C 2D 3D 4D 5D 6D", 0, Type.COMPARE }, // Straight Flush VS. Straight Flush
                { "2C 3C 4C 5C 7C 2D 3D 4D 5D 7D", 0, Type.COMPARE }, // Flush VS. Flush, the same High Card
                { "2C 3C 4C 5C 8C 2D 3D 4D 5D 7D", 1, Type.COMPARE }, // Flush VS. Flush, High Card 8 VS 7
                { "2C 3C 4C 5C 7C 2D 3D 4D 5D 8D", -1, Type.COMPARE }, // Flush VS. Flush, High Card 7 VS 8
                { "KC KD KS TC TD KC KD KS 9C 9D", 1, Type.COMPARE }, // Full House KT VS. Full House K9
                { "KC KD KS 9C 9D KC KD KS TC TD", -1, Type.COMPARE }, // Full House K9 VS. Full House KT
                { "KC KD KS TC TD QC QD QS TC TD", 1, Type.COMPARE }, // Full House KT VS. Full House QT
                { "QC QD QS TC TD KC KD KS TC TD", -1, Type.COMPARE }, // Full House QT VS. Full House KT
        });
    }

    @Test
    public void testFunctionGetHandValue() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Assume.assumeTrue(type == Type.GET_VALUE);
        // given
        playersHands = parser.parserRound(roundNotation);
        Method prepareHandToCompare = Class.forName("Poker.Hand").getDeclaredMethod("prepareHandToCompare");
        Method getHandValue = Class.forName("Poker.Hand").getDeclaredMethod("getHandValue");
        prepareHandToCompare.setAccessible(true);
        getHandValue.setAccessible(true);

        // when
        prepareHandToCompare.invoke(playersHands.get(0));
        Integer result = (Integer) getHandValue.invoke(playersHands.get(0));

        // then
        assertEquals(expectedHandValue, result);
    }

    @Test
    public void testFunctionCompareTo() {
        Assume.assumeTrue(type == Type.COMPARE);
        // given
        playersHands = parser.parserRound(roundNotation);

        // when
        Integer result = playersHands.get(0).compareTo(playersHands.get(1));

        // then
        assertEquals(expectedHandValue, result);
    }
}
