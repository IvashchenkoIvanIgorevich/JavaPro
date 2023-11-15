package homework.io;

import java.util.Map;
import java.util.stream.Collectors;

public class Statistic {

    private final Map<String, Integer> topWords;
    private final int uniqueWords;

    public Statistic(Map<String, Integer> topWords, int uniqueWords) {
        this.topWords = topWords;
        this.uniqueWords = uniqueWords;
    }

    @Override
    public String toString() {
        String result = topWords.entrySet().stream()
                .map(w -> new StringBuilder()
                        .append(w.getKey())
                        .append(" -> ")
                        .append(w.getValue()))
                .collect(Collectors.joining("\n"));
        return result + "\nTotal words: " + uniqueWords;
    }
}
