package org.example;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Command {

    SIGN_IN("Sign In", "1"),
    SIGN_UP("Sign Up", "2"),
    SHOW_ALL_USER_GAMES("Show all user games", "3"),
    BUY_GAME("Buy game", "4"),
    DEPOSIT("Deposit", "5"),
    LOGOUT("Log out", "0"),
    SHOW_ALL_GAMES("Show all games", "7"),
    EXIT("Exit", "6");

    private final String message;
    private final String key;

    Command(String message, String key) {
        this.message = message;
        this.key = key;
    }

    public static List<String> getMainMenuCommands() {
        return Arrays.asList(SIGN_IN.key + "-" + SIGN_IN.message, SIGN_UP.key + "-" + SIGN_UP.message,
                EXIT.key + "-" + EXIT.message);
    }

    public static List<String> getUserMenuCommands() {
        return Arrays.asList(SHOW_ALL_USER_GAMES.key + "-" + SHOW_ALL_USER_GAMES.message,
                SHOW_ALL_GAMES.key + "-" + SHOW_ALL_GAMES.message,
                BUY_GAME.key + "-" + BUY_GAME.message, DEPOSIT.key + "-" + DEPOSIT.message,
                LOGOUT.key + "-" + LOGOUT.message);
    }
}
