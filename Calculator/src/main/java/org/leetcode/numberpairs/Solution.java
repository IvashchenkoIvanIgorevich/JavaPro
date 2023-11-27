package org.leetcode.numberpairs;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> groupedNumbers = new HashMap<>();
        int result = 0;

        for (int num : nums) {
            if (groupedNumbers.containsKey(num)) {
                result += groupedNumbers.get(num);
            }
            groupedNumbers.put(num, groupedNumbers.getOrDefault(num, 0) + 1);
        }
        return result;
    }
}
