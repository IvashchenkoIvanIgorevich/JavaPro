package homework.practiceexception.exception;

public class ArrayDataException extends Exception {

    private final int causedRow;
    private final int causedColumn;

    public ArrayDataException(int causedRow, int causedColumn, String message, Throwable cause) {
        super(message, cause);
        this.causedRow = causedRow;
        this.causedColumn = causedColumn;
    }

    @Override
    public String getMessage() {
        return "The data located in [" + causedRow + "][" + causedColumn + "] couldn't be parsed to int. " + super.getMessage();
    }
}
