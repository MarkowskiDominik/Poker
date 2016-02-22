package main.java.Poker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Hand implements Comparable<Hand> {

    private final Integer NUMBER_OF_CARDS = 5;
    private final List<Card> cards;
    private List<Entry<Integer, Integer>> ranksOfCardWithNumberOfOccurrences;

    public Hand() {
        cards = new LinkedList<Card>();
    }

    public void addCard(Integer rank, String suit) {
        cards.add(new Card(rank, suit));
    }

    private Integer getRankOfCard(Integer index) {
        return ranksOfCardWithNumberOfOccurrences.get(index).getKey();
    }

    @Override
    public int compareTo(Hand otherHand) {
        prepareHandToCompare();
        otherHand.prepareHandToCompare();

        int result = getHandValue().compareTo(otherHand.getHandValue());
        if (result == 0) {
            result = compareHandsOfTheSameValue(otherHand);
        }
        return result;
    }

    private void prepareHandToCompare() {
        Collections.sort(cards, Collections.reverseOrder());
        createRanksOfCardWithNumberOfOccurrences();
    }

    private void createRanksOfCardWithNumberOfOccurrences() {
        Map<Integer, Integer> sourceForRanksOfCardWithNumberOfOccurrences = new TreeMap<Integer, Integer>();

        for (Card card : cards) {
            Integer count = 1;
            if (sourceForRanksOfCardWithNumberOfOccurrences.containsKey(card.getRank())) {
                count = sourceForRanksOfCardWithNumberOfOccurrences.get(card.getRank()) + 1;
            }
            sourceForRanksOfCardWithNumberOfOccurrences.put(card.getRank(), count);
        }

        ranksOfCardWithNumberOfOccurrences = new LinkedList<Map.Entry<Integer, Integer>>(
                sourceForRanksOfCardWithNumberOfOccurrences.entrySet());
        Collections.sort(ranksOfCardWithNumberOfOccurrences,
                Collections.reverseOrder(new ComparatorRanksOfCardWithNumberOfOccurrences()));
    }

    // REVIEW dmarkowski - please assign constants to the values and other magic numbers from this method
    private Integer getHandValue() {
        if (isStraight() && isFlush()) {
            return 8; // Straight Flush (Poker)
        }
        if (numberOfDifferentCardsIsEqual(2) && numberOfOccurrencesOfTheMostCommonCardIsEqual(4)) {
            return 7; // Four of a Kind
        }
        if (numberOfDifferentCardsIsEqual(2) && numberOfOccurrencesOfTheMostCommonCardIsEqual(3)) {
            return 6; // Full House
        }
        if (isFlush()) {
            return 5; // Flush
        }
        if (isStraight()) {
            return 4; // Straight
        }
        if (numberOfDifferentCardsIsEqual(3) && numberOfOccurrencesOfTheMostCommonCardIsEqual(3)) {
            return 3; // Three of a Kind
        }
        if (numberOfDifferentCardsIsEqual(3) && numberOfOccurrencesOfTheMostCommonCardIsEqual(2)) {
            return 2; // Two Pairs
        }
        if (numberOfDifferentCardsIsEqual(4)) {
            return 1; // One Pair
        }
        return 0; // High Card
    }

    private Boolean isStraight() {
        // REVIEW dmarkowski - to many ( ) brackets
        return (numberOfDifferentCardsIsEqual(5) && cards.get(0).getRank() - cards.get(4).getRank() == 4);
    }

    private Boolean isFlush() {
        String suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (!card.getSuit().equals(suit)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private Boolean numberOfDifferentCardsIsEqual(int number) {
        return (ranksOfCardWithNumberOfOccurrences.size() == number);
    }

    private Boolean numberOfOccurrencesOfTheMostCommonCardIsEqual(int number) {
        return ranksOfCardWithNumberOfOccurrences.get(0).getValue().equals(number);
    }

    private int compareHandsOfTheSameValue(Hand otherHand) {
        int result = 0;
        int index = 0;
        do {
            result = ranksOfCardWithNumberOfOccurrences.get(index).getKey().compareTo(otherHand.getRankOfCard(index));
            index++;
        } while (result == 0 && index < NUMBER_OF_CARDS);
        return result;
    }
}
