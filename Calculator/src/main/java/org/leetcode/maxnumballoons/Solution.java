package org.leetcode.maxnumballoons;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int maxNumberOfBalloons(String text) {
        List<Long> result = new ArrayList<>();
        for (char w : "balloon".toCharArray()) {
            long count = text.chars().filter(c -> c == w).count();
            if (w == 'l' || w == 'o') {
                count /= 2;
            }
            result.add(count);
        }
        return result.stream().mapToInt(Long::intValue).min().getAsInt();
    }
}
