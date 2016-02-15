package Poker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	Parser parser;
	
	@Before
	public void initializeParser() {
		parser = new Parser();
	}
	
	@After
	public void finalizeParser() {
		parser = null;
	}
	
	@Test
	public void test() {
		//given
		String round = "8C TS KC 9H 4S 7D 2S 5D 3S AC";
		
		//when
		parser.parserRound(round);
		
		//then
		assertTrue(true);
	}
}
