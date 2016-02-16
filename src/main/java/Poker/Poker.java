package Poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Poker {

	Scanner readFile;
	Parser parser;
	Logger logger = Logger.getLogger("Poker");
	
	public Poker(String fileName) throws FileNotFoundException {
		openFileToRead(fileName);
		parser = new Parser();
	}
	
	public void openFileToRead(String fileName) throws FileNotFoundException {
		readFile = new Scanner(new File(fileName));
	}
	
	public void closeFile() {
		readFile.close();
	}
	
	public Boolean playerOneIsWinner() {
		Integer winToLose = 0;
		List<Hand> playersHands = null;
		
		while (readFile.hasNextLine()) {
			try {
				System.out.println("try parser");
				playersHands = parser.parserRound(readFile.nextLine());
			} catch (Exception e) {
				logger.info("invalid structure in file");
			}
			winToLose = winToLose + (playersHands.get(0).compareTo(playersHands.get(1)));
		}
		closeFile();
		return (winToLose > 0);
	}
}
