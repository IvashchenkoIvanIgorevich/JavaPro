package org.example.service;

import lombok.Getter;
import org.example.AppData;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.dao.GameRepository;
import org.example.repository.dao.UserRepository;

import java.sql.Connection;

@Getter
public class Singleton {

    private static Singleton instance;
    private final UserService userService;
    private final GameService gameService;
    private final UserDataService userDataService;
    private final AppData appData;

    private Singleton(Connection connection) {
        UserRepository userRepository = new UserRepositoryImpl(connection);
        userService = new UserService(userRepository);

        GameRepository gameRepository = new GameRepositoryImpl(connection);
        gameService = new GameService(gameRepository);

        userDataService = new UserDataService(userRepository, gameRepository);

        appData = AppData.getInstance();
    }

    public static Singleton getInstance(Connection connection) {
        if (instance == null) {
            instance = new Singleton(connection);
        }
        return instance;
    }
}
