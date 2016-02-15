package Poker;

import java.util.ArrayList;

public class Parser {

	public Parser() {
	}

	public ArrayList<Hand> parserRound(String roundNotation) {
		ArrayList<Hand> playersHands = new ArrayList<Hand>(2);
		String[] cardsNotation = roundNotation.split(" ");

		playersHands.add(new Hand());
		playersHands.add(new Hand());

		for (int i = 0; i < cardsNotation.length; i++) {
			playersHands.get(i/5).addCard(parserCardRank(cardsNotation[i].substring(0, 1)), cardsNotation[i].substring(1, 2));
		}
		return playersHands;
	}

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
