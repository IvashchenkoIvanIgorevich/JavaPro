package leetcode;

import java.util.Set;
import java.util.TreeSet;

public class NumberHelper {

    public static boolean containsDuplicate(int[] numbers) {
        Set<Integer> sortedNumbers = new TreeSet<>();
        for (int number : numbers) {
            if (!sortedNumbers.add(number)) {
                return true;
            }
        }
        return false;
    }
}
