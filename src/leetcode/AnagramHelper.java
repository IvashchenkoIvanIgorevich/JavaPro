package leetcode;

import java.util.Arrays;

public class AnagramHelper {

    public static boolean isAnagram(String input, String anagram) {
        char[] anagramCharacters = anagram.toCharArray();
        char[] inputCharacters = input.toCharArray();
        Arrays.sort(anagramCharacters);
        Arrays.sort(inputCharacters);

        return Arrays.equals(anagramCharacters, inputCharacters);
    }
}
