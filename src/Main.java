import homework.io.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    private static final int TOP_WORDS = 10;
    private static final int WORD_LENGTH = 2;
    private static final String NAME_PREFIX = "_statistic.txt";
    private static final String EXIT_BUTTON = "y";
    private static final String SRC_DIRECTORY = "src";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        BookParser bookParser = new BookParser();
        StatisticService service = new StatisticService();

        while (!exit) {

            System.out.print(Messages.PLEASE_ENTER_FILE_NAME);
            Path bookLocation = Paths.get(SRC_DIRECTORY, scanner.nextLine());
            List<String> text;
            try (Stream<String> lines = Files.lines(bookLocation)) {
                text = bookParser.getWords(lines, WORD_LENGTH);
            } catch (NoSuchFileException e) {
                System.err.println(Messages.FILE_NOT_FOUND);
                continue;
            } catch (IOException e) {
                System.err.println(Messages.SMTH_GO_WRONG);
                continue;
            }

            Map<String, Integer> topWords = service.getPopularWords(text, TOP_WORDS);
            int uniqueWords = service.getUniqueWords(text);
            Statistic statistic = new Statistic(topWords, uniqueWords);

            String printableResults = statistic.toString();

            Path resultLocated = Paths.get(SRC_DIRECTORY, bookLocation.getFileName() + NAME_PREFIX);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultLocated.toFile()))) {
                writer.write(printableResults);
            } catch (IOException e) {
                System.err.println(Messages.FILE_NOT_SAVED);
            } catch (Exception e) {
                System.err.println(Messages.INTERNAL_ERROR);
            }

            System.out.println(printableResults);
            System.out.println(Messages.EXIT);
            exit = !scanner.next().equals(EXIT_BUTTON);
        }
        scanner.close();
    }
}
