import homework.io.PopularTOPWordStrategy;
import homework.io.Parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // Interaction with user
        String bookTitle;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Please enter the title of the book: ");
            bookTitle = scanner.nextLine();
            if (bookTitle.isEmpty()) {
                System.out.println("It seems like you haven't entered anything. Bye!!!");
                scanner.close();
                return;
            }
            System.out.println("You have entered: " + bookTitle + ". " +
                    "The result of the analysis will be saved in a file with the exact same name.");
        }

        // find book location
        Path bookLocated = Paths.get("src", bookTitle);
        // create an analysis strategy for getting top and unique words
        PopularTOPWordStrategy analysis = new PopularTOPWordStrategy(10);
        // main parser which can implement different analysis strategy
        Parser parser = new Parser(analysis);

        try (Stream<String> lines = Files.lines(bookLocated)) {
            // parse every line and analyze it
            parser.pars(lines);
        } catch (NoSuchFileException e) {
            System.out.println("The file named " + bookTitle + " could not be located.");
        } catch (IOException e) {
            System.out.println("Please get in touch with your administrator.");
        }

        String printableResults = analysis.getPrintableResults() + "\n" + "Total words: " + analysis.getUniqueWords();

        // write result to the file
        Path resultLocated = Paths.get("src", bookTitle + "_statistic.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultLocated.toFile()))) {
            writer.write(printableResults + "\n" + analysis.getUniqueWords());
        } catch (IOException e) {
            System.out.println("The file could not be saved.");
        } catch (Exception e) {
            System.out.println("Please get in touch with your administrator.");
        }

        System.out.println(printableResults);
    }
}
