package homework.io;

public enum Messages {

    PLEASE_ENTER_FILE_NAME("Please enter the title of the book: "),
    FILE_NOT_FOUND("File not found, please type it again..."),
    EXIT("Do you want to exit - y/n?"),
    INTERNAL_ERROR("Internal error, app will be closed, bye"),
    SMTH_GO_WRONG("Something go wrong. Please try again."),
    FILE_NOT_SAVED("The file could not be saved.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
