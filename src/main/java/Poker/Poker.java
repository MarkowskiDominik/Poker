package Poker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Poker {

	Parser parser;
	BufferedReader fileReader;
	Logger logger = Logger.getLogger("Poker");
	
	public Poker(String fileName) {
		openFileToRead(fileName);
		parser = new Parser();
	}
	
	private void openFileToRead(String fileName) {
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			logger.info("file not found");
		}
	}
	
	private void closeFile() throws IOException {
		fileReader.close();
	}
	
	public Integer numberOfWinsFirstPlayer() throws IOException {
		Integer numberOfWin = 0;
		List<Hand> playersHands = null;
		
		while (fileReader.ready()) {
			try {
				playersHands = parser.parserRound(fileReader.readLine());
			} catch (Exception e) {
				logger.info("invalid structure in file");
			}
			if (playersHands.get(0).compareTo(playersHands.get(1)) == 1) {
				numberOfWin++;
			}
		}
		closeFile();
		logger.info(numberOfWin.toString());
		return numberOfWin;
	}
}
