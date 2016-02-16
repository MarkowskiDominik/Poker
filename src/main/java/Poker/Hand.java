package Poker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
		Collections.sort(listCard, Collections.reverseOrder());
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

	private Integer getHandValue() {
		if (isStraight() && isFlush()) {
			return 8; // Straight Flush (Poker)
		}
		if (isNumberOfDifferentCards(2) && isNumberOfOccurencesCard(4)) {
			return 7; // Four of a Kind
		}
		if (isNumberOfDifferentCards(2) && isNumberOfOccurencesCard(3)) {
			return 6; // Full House
		}
		if (isFlush()) {
			return 5; // Flush
		}
		if (isStraight()) {
			return 4; // Straight
		}
		if (isNumberOfDifferentCards(3) && isNumberOfOccurencesCard(3)) {
			return 3; // Three of a Kind
		}
		if (isNumberOfDifferentCards(3) && isNumberOfOccurencesCard(2)) {
			return 2; // Two Pairs
		}
		if (isNumberOfDifferentCards(4)) {
			return 1; // One Pair
		}
		return 0; // High Card
	}

	private Boolean isStraight() {
		return (isNumberOfDifferentCards(5) && listCard.get(0).getRank() - listCard.get(4).getRank() == 4);
	}

	private Boolean isFlush() {
		String suit = listCard.get(0).getSuit();
		for (int i = 1; i < listCard.size(); i++) {
			if (!suit.equals(listCard.get(i).getSuit())) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	private Boolean isNumberOfDifferentCards(int number) {
		return (numberOfOccurrences.size() == number);
	}
	
	private Boolean isNumberOfOccurencesCard(int number) {
		return (numberOfOccurrences.values().contains(number));
	}

	private int compareHandsOfTheSameValue(Hand otherHand) {
		int result = 0;
		int index = 0;
		do {
			result = listCard.get(index).compareTo(otherHand.getCard(index));
			index++;
		} while (result == 0 && index < NUMBER_OF_CARDS );
		return result;
	}
}
