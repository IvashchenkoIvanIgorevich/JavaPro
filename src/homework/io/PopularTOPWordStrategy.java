package homework.io;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PopularTOPWordStrategy implements AnalysisStrategy {

    private static final int WORD_LENGTH = 3;
    private final int top;
    private final Map<String, Integer> results;

    public PopularTOPWordStrategy(int top) {
        this.results = new TreeMap<>();
        this.top = top;
    }

    public int getUniqueWords() {
        return results.entrySet().size();
    }

    public String getPrintableResults() {
        return results.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(top)
                .map(w -> new StringBuilder()
                        .append(w.getKey())
                        .append(" -> ")
                        .append(w.getValue()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void apply(Stream<String> lines) {
        Stream<String> words = getWords(lines);

        words.filter(word -> word.length() > WORD_LENGTH)
                .forEach(this::populate);
    }

    private void populate(String word) {
        if (results.containsKey(word)) {
            Integer count = results.get(word) + 1;
            results.replace(word, count);
        } else {
            results.put(word, 1);
        }
    }

    private Stream<String> getWords(Stream<String> lines) {
        return lines.flatMap(line -> Arrays.stream(line.split("\\P{L}+")));
    }
}
