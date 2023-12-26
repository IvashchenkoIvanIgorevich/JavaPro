package org.example.service;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }
}
