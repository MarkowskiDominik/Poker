package Poker;

import java.util.ArrayList;
import java.util.List;

public class Parser {

	private static final int NUMBER_OF_CARDS_IN_ROUND = 10;
	private static final int ACE_VALUE = 14;
	private static final int KING_VALUE = 13;
	private static final int QUEEN_VALUE = 12;
	private static final int JACK_VALUE = 11;
	private static final int TEEN_VALUE = 10;
	private static final String CARDS_SPLIT = " ";
	private static final String CARD_FORMAT = "[0-9TJQKA][SHCD]";

	public Parser() {
	}

	public List<Hand> parserRound(String roundNotation) throws IllegalArgumentException {
		List<Hand> playersHands = new ArrayList<Hand>(2);
		String[] cardsNotation = roundNotation.split(CARDS_SPLIT);

		if (cardsNotation.length != NUMBER_OF_CARDS_IN_ROUND) {
			throw new IllegalArgumentException("incorrect number of cards in the round");
		}

		playersHands.add(new Hand());
		playersHands.add(new Hand());

		for (int numberOfCard = 0; numberOfCard < NUMBER_OF_CARDS_IN_ROUND; numberOfCard++) {
			if (!cardsNotation[numberOfCard].matches(CARD_FORMAT)) {
				throw new IllegalArgumentException("invalid card format");
			}
			playersHands.get(getNumberOfPlayer(numberOfCard)).addCard(
					parserCardRank(cardsNotation[numberOfCard].substring(0, 1)),
					cardsNotation[numberOfCard].substring(1, 2));
		}

		return playersHands;
	}

	private int getNumberOfPlayer(int numberOfCard) {
		return numberOfCard / 5;
	}

	private Integer parserCardRank(String cardNotation) {
		switch (cardNotation.charAt(0)) {
		case 'T':
			return TEEN_VALUE;
		case 'J':
			return JACK_VALUE;
		case 'Q':
			return QUEEN_VALUE;
		case 'K':
			return KING_VALUE;
		case 'A':
			return ACE_VALUE;
		default:
			return Integer.parseInt(cardNotation);
		}
	}
}
