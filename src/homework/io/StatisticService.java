package homework.io;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticService {

    public Map<String, Integer> getPopularWords(List<String> words, int amount) {
        return words.stream()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.summingInt(e -> 1)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(amount)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public int getUniqueWords(List<String> words) {
        return new HashSet<>(words).size();
    }
}
