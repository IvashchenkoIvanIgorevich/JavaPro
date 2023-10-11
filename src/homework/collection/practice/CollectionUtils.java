package homework.collection.practice;

import java.util.List;
import java.util.ArrayList;

public class CollectionUtils {

    public static int countOccurrence(List<String> words, String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            // TODO: better to make displayable exception
            return 0;
        }

        int countOccur = 0;
        String searchWordCapitalized = searchWord.toUpperCase().trim();
        for (String word : words) {
            if (word == null) {
                continue;
            }

            String wordCapitalized = word.toUpperCase().trim();
            if (searchWordCapitalized.equals(wordCapitalized)) {
                countOccur++;
            }
        }

        return countOccur;
    }

    public static List<Integer> toList(int[] numbers) {
        List<Integer> result = new ArrayList<>();

        for (int number : numbers) {
            result.add(number);
        }

        return result;
    }

    public static List<Integer> findUnique(List<Integer> numbers) {
        List<Integer> uniqueNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (number == null) {
                continue;
            }

            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }
        return uniqueNumbers;
    }

    public static void calcOccurrence(List<String> words) {
        List<String> presentedWords = new ArrayList<>();
        for (String word : words) {
            if (word == null || presentedWords.contains(word)) {
                continue;
            }
            presentedWords.add(word);

            System.out.println(word + " : " + countOccurrence(words, word));
        }
    }

    public static List<WordOccurrenceDto> findOccurrence(List<String> words) {
        List<WordOccurrenceDto> wordOccurrenceDtos = new ArrayList<>();

        for (String word : words) {
            if (word == null || containsSpecificWord(wordOccurrenceDtos, word)) {
                continue;
            }

            int numberOfOccurrence = countOccurrence(words, word);
            wordOccurrenceDtos.add(new WordOccurrenceDto(word, numberOfOccurrence));
        }

        return wordOccurrenceDtos;
    }

    private static boolean containsSpecificWord(List<WordOccurrenceDto> wordOccurrenceDtos, String word) {
        for (WordOccurrenceDto wordOccurrenceDto : wordOccurrenceDtos) {
            if (word.equals(wordOccurrenceDto.getName())) {
                return true;
            }
        }
        return false;
    }
}
