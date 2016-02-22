package main.java.Poker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Poker {

    // REVIEW dmarkowski - missing private modifiers for the variables, additionally all which doesn't change should be
    // final
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

        // REVIEW dmarkowski - Please separate read and business logic, since the input data is not big it can be read
        // as whole by the program. Currently reading the input and processing is mixed
        while (fileReader.ready()) {
            try {
                playersHands = parser.parserRound(fileReader.readLine());
            } catch (Exception e) {
                // REVIEW dmarkowski - to general catch clause, it will also catch all programmers errors, which are not
                // connected with the file conditions
                logger.info("invalid structure in file");
            }
            // REVIEW dmarkowski - pottential NPE when the parser throws an exception, then the playersHands is null
            if (playersHands.get(0).compareTo(playersHands.get(1)) == 1) {
                numberOfWin++;
            }
        }
        closeFile();
        logger.info(numberOfWin.toString());
        return numberOfWin;
    }
}
