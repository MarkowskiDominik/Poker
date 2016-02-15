package Poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Poker {

	Scanner readFile;
	Parser parser;
	
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
		while (readFile.hasNextLine()) {
			ArrayList<Hand> playersHands = parser.parserRound(readFile.nextLine());
			winToLose = winToLose + (playersHands.get(0).compareTo(playersHands.get(1)));
		}
		closeFile();
		return (winToLose > 0);
	}
}
