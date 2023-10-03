package leetcode;

import java.util.Arrays;

public class AnagramHelper {

    public static boolean isAnagram(String input, String anagram) {
        char[] charsAnagram = anagram.toCharArray();
        char[] charsInput = input.toCharArray();
        Arrays.sort(charsAnagram);
        Arrays.sort(charsInput);

        return Arrays.equals(charsAnagram, charsInput);
    }
}
