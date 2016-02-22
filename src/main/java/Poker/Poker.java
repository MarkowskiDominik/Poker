package Poker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Poker {

    private final Parser parser;
    private BufferedReader fileReader;
    private final Logger logger = Logger.getLogger("Poker");

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

    public Integer numberOfWinsFirstPlayer() throws IOException, IllegalArgumentException {
        Integer numberOfWin = 0;
        List<Hand> playersHands = null;

        while (isDataInFile()) {
        	playersHands = parser.parserRound(getNewRoundFromFile());

        	if (playersHands.get(0).compareTo(playersHands.get(1)) == 1) {
                numberOfWin++;
            }
        }
        closeFile();
        logger.info(numberOfWin.toString());
        return numberOfWin;
    }

	private boolean isDataInFile() throws IOException {
		return fileReader.ready();
	}
	
	private String getNewRoundFromFile() throws IOException {
		return fileReader.readLine();
	}
	
    private void closeFile() throws IOException {
        fileReader.close();
    }
}
