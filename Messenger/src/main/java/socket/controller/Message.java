package socket.controller;

public enum Message {

    REQUEST_NICKNAME("Вкажіть свій nickname: "),
    GREETINGS("Привіт"),
    UKRAINIAN_VERIFICATION_QUESTION("Що таке паляниця?"),
    RIGHT_ANSWER("хліб"),
    RESPONSE_RIGHT_ANSWER("Гарного дня. "),
    RESPONSE_WRONG_ANSWER("╭∩╮（︶_︶）╭∩╮"),
    SERVER_RESPONSE("Server: "),
    CONNECTED_TO_CHAT(" у чаті!"),
    WRITE_MESSAGE(" відправлено: "),
    LEFT_CHAT(" покинула(в) чат!"),
    EXIT("quit");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
