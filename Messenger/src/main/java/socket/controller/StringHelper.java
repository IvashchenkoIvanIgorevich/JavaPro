package socket.controller;

public class StringHelper {

    public static boolean isRussianCharsPresent(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Input text is a required argument");
        }
        return text.matches(".*[ыэъ].*");
    }
}
