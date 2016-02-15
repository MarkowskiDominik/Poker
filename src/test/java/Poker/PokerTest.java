package Poker;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PokerTest {
	
	Poker poker;
	
	@Before
	public void initializePoker() {
		try {
			poker = new Poker("src\\test\\resources\\Poker\\poker.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void finalizePoker() {
		poker = null;
	}
	
	@Test
	public void test() {
		//given
		
		//when
		
		//then
		fail("Not yet implemented");
	}
}
