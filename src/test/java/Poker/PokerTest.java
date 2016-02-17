package Poker;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PokerTest {
	
	private Poker poker;
	private String filePath;
	private Integer expected;
	
	public PokerTest(String path, Integer expected) {
		this.filePath = path;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> testedHands() {
		return Arrays.asList(new Object[][] {
			{ "src\\test\\resources\\Poker\\poker.txt", 376 },
			{ "src\\test\\resources\\Poker\\test1.txt", 46 },
		});
	}
	
	@Test
	public void playerOneIsWinner() throws IOException {
		//given		
		//when
		poker = new Poker(filePath);
		
		//then
		assertEquals(expected, poker.numberOfWinsFirstPlayer());
	}
}
