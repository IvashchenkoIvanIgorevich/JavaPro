package homework.io;

import java.util.stream.Stream;

public class Parser {

    private final AnalysisStrategy analysisStrategy;

    public Parser(AnalysisStrategy analysisStrategy) {
        this.analysisStrategy = analysisStrategy;
    }

    public void pars(Stream<String> line) {
        analysisStrategy.apply(line);
    }
}
