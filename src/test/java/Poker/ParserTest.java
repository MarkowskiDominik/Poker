package Poker;

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
	
	@Test(expected = Exception.class)
	public void toManyCards() {
		//given
		String round = "KC QC AC JC TC 7D 2S 5D 3S AC AC";
		
		//when		
		//then
		parser.parserRound(round);
	}
	
	@Test(expected = Exception.class)
	public void noTeenCards() {
		//given
		String round = "KC QC AC JC TC 7D 2S 5D 3S";
		
		//when		
		//then
		parser.parserRound(round);
	}

	@Test(expected = Exception.class)
	public void invalidCardRankValue() {
		//given
		String round = "ZC ZC AC JC TC 7D 2S 5D 3S AC";
		
		//when		
		//then
		parser.parserRound(round);
	}
	
	@Test(expected = Exception.class)
	public void invalidCardSuitValue() {
		//given
		String round = "KZ QZ AC JC TC 7D 2S 5D 3S AC";
		
		//when		
		//then
		parser.parserRound(round);
	}
}
