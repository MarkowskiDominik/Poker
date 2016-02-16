package Poker;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class PokerTest {
	
	Poker poker;
	
	@Test
	public void test() {
		//given		
		//when
		try {
			poker = new Poker("src\\test\\resources\\Poker\\poker.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//then
		assertFalse(poker.playerOneIsWinner());
	}
}
