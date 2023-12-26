package org.example.repository;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;
import org.example.repository.mapper.GameMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {

    private static final String SQL_SELECT_GAME_BY_ID =
            """
                    SELECT * FROM "game"
                    WHERE "game".id = ?
                    """;

    private static final String SQL_SELECT_GAMES =
            """
                    SELECT * FROM "game"
                    """;

    private final Connection connection;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Game getGameById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_GAME_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return GameMapper.mapResultSetToGame(resultSet);
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Game> getGamesByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();

        try {
            String query = getSqlSelectGamesById(ids);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < ids.size(); i++) {
                Integer id = ids.get(i);
                preparedStatement.setInt(i + 1, id);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Game> result = new ArrayList<>();
            while (resultSet.next()) {
                Game game = GameMapper.mapResultSetToGame(resultSet);
                result.add(game);
            }

            return result;
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    private String getSqlSelectGamesById(List<Integer> ids) {
        StringBuilder sql = new StringBuilder("SELECT * FROM game WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            sql.append("?");
            if (i < ids.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");
        return sql.toString();
    }

    @Override
    public List<Game> getAllGames() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_GAMES);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Game> result = new ArrayList<>();
            while (resultSet.next()) {
                Game game = GameMapper.mapResultSetToGame(resultSet);
                result.add(game);
            }

            return result;
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
