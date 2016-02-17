package Poker;

import java.util.Comparator;
import java.util.Map.Entry;

public class ComparatorRanksOfCardWithNumberOfOccurrences implements Comparator<Entry<Integer, Integer>> {
	@Override
	public int compare(Entry<Integer, Integer> first, Entry<Integer, Integer> second) {
		int result = first.getValue().compareTo(second.getValue());
		if (result == 0) {
			result = first.getKey().compareTo(second.getKey());
		}
		return result;
	}
}
