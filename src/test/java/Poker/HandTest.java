package Poker;

import static org.junit.Assert.*;

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

@RunWith(Parameterized.class)
public class HandTest {
	
	enum Type {GET_VALUE, COMPARE};
	private List<Hand> playersHands;
	private Parser parser;
	private String roundNotation;
	private Integer expectedHandValue;
	private Type type;
	
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
		return Arrays.asList(new Object[][] {
			{ "9S QC AC JC TC 2D 3D 4D 5D 6D", 0, Type.GET_VALUE }, // High Card
			{ "KC QC KS JC TC 2D 3D 4D 5D 6D", 1, Type.GET_VALUE }, // One Pair
			{ "KC QC AC KS QS 2D 3D 4D 5D 6D", 2, Type.GET_VALUE }, // Two Pairs
			{ "KC KS AC JC KD 2D 3D 4D 5D 6D", 3, Type.GET_VALUE }, // Three of a Kind
			{ "KS QC AC JC TC 2D 3D 4D 5D 6D", 4, Type.GET_VALUE }, // Straight
			{ "9C QC AC JC TC 2D 3D 4D 5D 6D", 5, Type.GET_VALUE }, // Flush
			{ "KC QC KS QS KH 2D 3D 4D 5D 6D", 6, Type.GET_VALUE }, // Full House
			{ "KC KS AC KD KH 2D 3D 4D 5D 6D", 7, Type.GET_VALUE }, // Four of a Kind
			{ "KC QC AC JC TC 2D 3D 4D 5D 6D", 8, Type.GET_VALUE }, // Straight Flush
			{ "2C 3C 4C 5C 6C 2D 3D 4D 5D 6D", 0, Type.COMPARE }
		});
	}
	
	@Test
	public void testGetHandValueFunction() {
		Assume.assumeTrue(type == Type.GET_VALUE);
		//given
		playersHands = parser.parserRound(roundNotation);
		
		//when
		//then
		assertEquals(expectedHandValue, playersHands.get(0).getHandValue());
	}
	
	@Test
	public void testCompareToFunction() {
		Assume.assumeTrue(type == Type.COMPARE);
		//given
		playersHands = parser.parserRound(roundNotation);
		Integer result = playersHands.get(0).compareTo(playersHands.get(1));
		
		//when
		//then
		assertEquals(expectedHandValue, result);
	}
}
