package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {

    Game getGameById(int id);

    List<Game> getGamesById(List<Integer> ids);

    List<Game> getAllGames();
}
