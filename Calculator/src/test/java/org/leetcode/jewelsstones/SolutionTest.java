package org.leetcode.jewelsstones;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void numJewelsInStonesReturnCorrectCount() {
        String stones = "aaAAbbb";
        String jewels = "aA";

        int actual = Solution.numJewelsInStones(jewels, stones);

        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void numJewelsInStones_EmptyFirstArgument_ReturnZeroCount() {
        String stones = "";
        String jewels = "aA";

        int actual = Solution.numJewelsInStones(jewels, stones);

        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void numJewelsInStones_EmptySecondArgument_ReturnZeroCount() {
        String stones = "aaAAbbb";
        String jewels = "";

        int actual = Solution.numJewelsInStones(jewels, stones);

        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void numJewelsInStones_NullFirstArgument_ThrowsIllegalArgumentException() {
        String stones = "aaAAbbb";
        String jewels = null;

        Solution.numJewelsInStones(jewels, stones);
    }

    @Test(expected = IllegalArgumentException.class)
    public void numJewelsInStones_NullSecondArgument_ThrowsIllegalArgumentException() {
        String stones = null;
        String jewels = "aA";

        Solution.numJewelsInStones(jewels, stones);
    }
}