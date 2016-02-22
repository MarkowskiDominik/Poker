package Poker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Hand implements Comparable<Hand> {

    private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int HIGH_CARD_VALUE = 0;
	private static final int ONE_PAIR_VALUE = 1;
	private static final int TWO_PAIRS_VALUE = 2;
	private static final int THREE_OF_A_KIND_VALUE = 3;
	private static final int STRAIGHT_VALUE = 4;
	private static final int FLUSH_VALUE = 5;
	private static final int FULL_HOUSE_VALUE = 6;
	private static final int FOUR_OF_A_KINF_VALUE = 7;
	private static final int STRAIGHT_FLUSH_VALUE = 8;
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

    private Integer getHandValue() {
        if (isStraight() && isFlush()) {
            return STRAIGHT_FLUSH_VALUE; // Straight Flush (Poker)
        }
        if (numberOfDifferentCardsIsEqual(TWO) && numberOfOccurrencesOfTheMostCommonCardIsEqual(FOUR)) {
            return FOUR_OF_A_KINF_VALUE; // Four of a Kind
        }
        if (numberOfDifferentCardsIsEqual(TWO) && numberOfOccurrencesOfTheMostCommonCardIsEqual(THREE)) {
            return FULL_HOUSE_VALUE; // Full House
        }
        if (isFlush()) {
            return FLUSH_VALUE; // Flush
        }
        if (isStraight()) {
            return STRAIGHT_VALUE; // Straight
        }
        if (numberOfDifferentCardsIsEqual(THREE) && numberOfOccurrencesOfTheMostCommonCardIsEqual(THREE)) {
            return THREE_OF_A_KIND_VALUE; // Three of a Kind
        }
        if (numberOfDifferentCardsIsEqual(THREE) && numberOfOccurrencesOfTheMostCommonCardIsEqual(TWO)) {
            return TWO_PAIRS_VALUE; // Two Pairs
        }
        if (numberOfDifferentCardsIsEqual(FOUR)) {
            return ONE_PAIR_VALUE; // One Pair
        }
        return HIGH_CARD_VALUE; // High Card
    }

    private Boolean isStraight() {
        return numberOfDifferentCardsIsEqual(FIVE) && differenceRankBetweenFirstAndLastCardIsFour();
    }

	private boolean differenceRankBetweenFirstAndLastCardIsFour() {
		return cards.get(0).getRank() - cards.get(4).getRank() == 4;
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
        return ranksOfCardWithNumberOfOccurrences.size() == number;
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
