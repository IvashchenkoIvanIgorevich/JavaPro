package homework.collection.practice;

public class WordOccurrenceDto {

    private final String name;
    private final int numberOfOccurrence;

    public WordOccurrenceDto(String name, int numberOfOccurrence) {
        this.name = name;
        this.numberOfOccurrence = numberOfOccurrence;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{name: \"" + name + "\", occurrence: " + numberOfOccurrence + "}";
    }
}
