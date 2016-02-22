package main.java.Poker;

public class Card implements Comparable<Card> {

    private final Integer rank;
    private final String suit;

    public Card(Integer value, String suit) {
        this.rank = value;
        this.suit = suit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        if (rank == null) {
            if (other.rank != null) {
                return false;
            }
        }
        else if (!rank.equals(other.rank)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Card otherCard) {
        return rank.compareTo(otherCard.rank);
    }

    @Override
    public String toString() {
        return rank + suit;
    }

    public Integer getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}