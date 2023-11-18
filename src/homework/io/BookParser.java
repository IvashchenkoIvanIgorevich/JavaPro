package homework.io;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookParser {

    public List<String> getWords(Stream<String> lines, int wordLength) {
        return lines
                .flatMap(line -> Arrays.stream(line.split("\\P{L}+")))
                .filter(word -> word.length() > wordLength)
                .collect(Collectors.toList());
    }
}
