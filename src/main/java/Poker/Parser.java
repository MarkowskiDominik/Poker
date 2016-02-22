package main.java.Poker;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    // REVIEW dmarkowski - why not defined as static?
    private final String CARD_FORMAT = "[0-9TJQKA][SHCD]";
    private final Integer NUMBER_OF_CARDS_IN_ROUND = 10;

    public Parser() {
    }

    public List<Hand> parserRound(String roundNotation) {
        List<Hand> playersHands = new ArrayList<Hand>(2);
        String[] cardsNotation = roundNotation.split(" ");

        if (!NUMBER_OF_CARDS_IN_ROUND.equals(cardsNotation.length)) {
            throw new IllegalArgumentException("incorrect number of cards in the round");
        }

        playersHands.add(new Hand());
        playersHands.add(new Hand());

        for (int i = 0; i < NUMBER_OF_CARDS_IN_ROUND; i++) {
            if (!cardsNotation[i].matches(CARD_FORMAT)) {
                throw new IllegalArgumentException("invalid card format");
            }
            // REVIEW dmarkowski - i / 5 ??? please define clearly what hand is processed
            playersHands.get(i / 5).addCard(parserCardRank(cardsNotation[i].substring(0, 1)),
                    cardsNotation[i].substring(1, 2));
        }

        return playersHands;
    }

    // REVIEW dmarkowski - please use contants or an enum for the values
    private Integer parserCardRank(String cardNotation) {
        switch (cardNotation.charAt(0)) {
        case 'T':
            return 10;
        case 'J':
            return 11;
        case 'Q':
            return 12;
        case 'K':
            return 13;
        case 'A':
            return 14;
        default:
            return Integer.parseInt(cardNotation);
        }
    }
}
