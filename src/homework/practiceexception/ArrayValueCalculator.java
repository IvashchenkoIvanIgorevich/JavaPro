package homework.practiceexception;

import homework.practiceexception.exception.ArrayDataException;
import homework.practiceexception.exception.ArraySizeException;

public class ArrayValueCalculator {

    private static final int MAX_ARRAY_LENGTH = 4;

    public static int doCalc(String[][] input) throws Exception {
        int result = 0;
        try {
            if (input.length != input[0].length || input.length != MAX_ARRAY_LENGTH) {
                throw new ArraySizeException("Incorrect array size.");
            }

            for (int row = 0; row < input.length; row++) {
                for (int col = 0; col < input[0].length; col++) {
                    result += getParsedData(input[row][col], row, col);
                }
            }
        } catch (Exception e) {
            throw e;    // maybe it would be better, but I'm not sure - (Exception)e.fillInStackTrace();
        }

        return result;
    }

    private static int getParsedData(String input, int row, int col) throws ArrayDataException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ArrayDataException(row, col, e.getMessage(), e);
        }
    }
}
