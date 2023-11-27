package org.leetcode.maxnumballoons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void maxNumberOfBalloonsReturnCorrectCount() {
        String text = "loonbalxballpoon";

        int actual = Solution.maxNumberOfBalloons(text);

        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void maxNumberOfBalloons_NullArgument_ThrowNullPointerException() {
        String text = null;

        Solution.maxNumberOfBalloons(text);
    }

    @Test
    public void maxNumberOfBalloons_EmptyArgument_ReturnZeroCount() {
        String text = "";

        int actual = Solution.maxNumberOfBalloons(text);

        int expected = 0;
        assertEquals(expected, actual);
    }
}