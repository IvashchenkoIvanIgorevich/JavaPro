package org.leetcode.jewelsstones;

public class Solution {

    public static int numJewelsInStones(String jewels, String stones) {
        if (stones == null || jewels == null) {
            throw new IllegalArgumentException("Invalid input arguments");
        }

        return Math.toIntExact(stones.chars().filter(s -> jewels.indexOf(s) >= 0).count());
    }
}
