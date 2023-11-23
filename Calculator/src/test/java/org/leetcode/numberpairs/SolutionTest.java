package org.leetcode.numberpairs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void numIdenticalPairsReturnCorrectCount() {
        int[] nums = {1,2,3,1,1,3};

        int actual = Solution.numIdenticalPairs(nums);

        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void numIdenticalPairs_NullArgument_ThrowNullPointerException() {
        Solution.numIdenticalPairs(null);
    }

    @Test
    public void numIdenticalPairs_EmptyArgument_ReturnZeroCount() {
        int[] nums = new int[0];

        int actual = Solution.numIdenticalPairs(nums);

        int expected = 0;
        assertEquals(expected, actual);
    }
}