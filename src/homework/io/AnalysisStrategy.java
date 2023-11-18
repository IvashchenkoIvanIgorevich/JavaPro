package homework.io;

import java.util.stream.Stream;

/**
 * This interface represents creating new analysis strategy manipulating with line
 *
 * https://refactoring.guru/design-patterns/strategy
 */
public interface AnalysisStrategy {

    void apply(Stream<String> line);
}
