package Poker;

import java.util.Map;
import java.util.TreeMap;

public class Hand implements Comparable<Hand>{
	Map<Card, Integer> numberOfOccurrences;

	public Hand() {
		numberOfOccurrences = new TreeMap<Card, Integer>();
	}

	public void addCard(Integer rank, String color) {
		Card card = new Card(rank, color);
		Integer count = 1;

		if (numberOfOccurrences.containsKey(card)) {
            count = numberOfOccurrences.get(card) + 1;
        }
		numberOfOccurrences.put(card, count);
	}

	public int compareTo(Hand otherHand) {
		int result = getHandValue().compareTo(otherHand.getHandValue());
		if (result == 0) {
			
		}
		return result;
	}

	public Integer getHandValue() {
		return 0;
	}

	public Boolean isFlush() {
		return Boolean.TRUE;
	}
	
	public void showHand() {
		for (Map.Entry<Card, Integer> card : numberOfOccurrences.entrySet()){
			System.out.println(card.getKey().rank);
		}
	}
}
