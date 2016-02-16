package Poker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Hand implements Comparable<Hand> {

	private final Integer NUMBER_OF_CARDS = 5;
	List<Card> listCard;
	Map<Card, Integer> numberOfOccurrences;

	public Hand() {
		listCard = new LinkedList<Card>();
		numberOfOccurrences = new TreeMap<Card, Integer>();
	}

	public void addCard(Integer rank, String suit) {
		Card card = new Card(rank, suit);
		Integer count = 1;

		if (numberOfOccurrences.containsKey(card)) {
			count = numberOfOccurrences.get(card) + 1;
		}
		numberOfOccurrences.put(card, count);
		listCard.add(new Card(rank, suit));
		Collections.sort(listCard);
	}
	
	public Card getCard(Integer index) {
		return listCard.get(index); 
	}

	public int compareTo(Hand otherHand) {
		int result = getHandValue().compareTo(otherHand.getHandValue());
		if (result == 0) {
			result = compareHandsOfTheSameValue(otherHand);
		}
		return result;
	}

	public Integer getHandValue() {
		if (isStraight() && isFlush()) {
			return 8; // Straight Flush (Poker)
		}
		if (isTwoDifferentCards() && numberOfOccurrences.values().contains(Integer.valueOf(4))) {
			return 7; // Four of a Kind
		}
		if (isTwoDifferentCards() && numberOfOccurrences.values().contains(Integer.valueOf(3))) {
			return 6; // Full House
		}
		if (isFlush()) {
			return 5; // Flush
		}
		if (isStraight()) {
			return 4; // Straight
		}
		if (isThreeDifferentCards() && numberOfOccurrences.values().contains(Integer.valueOf(3))) {
			return 3; // Three of a Kind
		}
		if (isThreeDifferentCards() && numberOfOccurrences.values().contains(Integer.valueOf(2))) {
			return 2; // Two Pairs
		}
		if (isFourDifferentCards()) {
			return 1; // One Pair
		}
		return 0; // High Card
	}

	public Boolean isStraight() {
		return (isFiveDifferentCards() && listCard.get(0).rank - listCard.get(4).rank == 4);
	}

	public Boolean isFlush() {
		String suit = listCard.get(0).suit;
		for (int i = 1; i < listCard.size(); i++) {
			if (!suit.equals(listCard.get(i).suit)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	private Boolean isTwoDifferentCards() {
		return (numberOfOccurrences.size() == 2);
	}

	private Boolean isThreeDifferentCards() {
		return (numberOfOccurrences.size() == 3);
	}

	private Boolean isFourDifferentCards() {
		return (numberOfOccurrences.size() == 4);
	}

	private Boolean isFiveDifferentCards() {
		return (numberOfOccurrences.size() == 5);
	}

	private int compareHandsOfTheSameValue(Hand otherHand) {
		int result = 0;
		int index = 0;
		do {
			result = listCard.get(index).compareTo(otherHand.getCard(index));
			index++;
		} while (result != 0 && index < NUMBER_OF_CARDS );
		return result;
	}

	public void showHand() {
		for (Entry<Card, Integer> entry : numberOfOccurrences.entrySet()) {
			//System.out.println(entry.getKey().rank + entry.getKey().suit + "  " + entry.getValue());
		}
		for (Card card : listCard) {
			System.out.println(card.rank + card.suit);
		}
	}
}
