package org.example.service.mock;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GameRepositoryMock implements GameRepository {

    private List<Game> games;

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }

    @Override
    public Game getGameById(int id) {
        return games.get(id);
    }

    @Override
    public List<Game> getGamesByIds(List<Integer> ids) {
        return games.stream().filter(g -> ids.contains(g.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }
}
