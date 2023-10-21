package leetcode;

public class Palindrome {

    public static boolean isPalindrome(String phrase) {
        phrase = phrase.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        return phrase.contentEquals(new StringBuilder(phrase).reverse());
    }
}
