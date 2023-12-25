package org.example.repository;

import org.example.AppData;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_SELECT_USER =
            """
                    SELECT * FROM "user"
                    WHERE "user".nickname = ? AND "user".password = ? 
                    """;

    private static final String SQL_SELECT_GAME_ID_BY_USER =
            """
                    SELECT "user_game".game_id FROM "user_game" 
                    WHERE "user_game".user_id = ?
                    """;

    private static final String SQL_INSERT_USER =
            """
                    INSERT INTO "user"(nickname, password, birthday, amount) 
                    VALUES (?, ?, ?, ?);
                    """;

    private static final String SQL_UPDATE_AMOUNT =
            """
                    UPDATE "user"
                    SET amount = ?
                    WHERE id = ?
                    RETURNING *
                    """;

    private static final String SQL_INSERT_GAME_TO_USER =
            """
                    INSERT INTO "user_game"(user_id, game_id)
                    	VALUES (?, ?);
                    """;

    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User get(String nickname, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .nickname(resultSet.getString("nickname"))
                        .password(resultSet.getString("password"))
                        .birthday(resultSet.getDate("birthday"))
                        .amount(resultSet.getInt("amount"))
                        .build();
            }
            return null;
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
    public User save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, new Date(user.getBirthday().getTime()));
            preparedStatement.setInt(4, user.getAmount());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));

            return user;
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
    public List<Integer> getUserGameIds(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_GAME_ID_BY_USER);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Integer> result = new ArrayList<>();
            while (resultSet.next()) {
                Integer gameId = resultSet.getInt("game_id");
                result.add(gameId);
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

    @Override
    public void updateDeposit(int userId, int amount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_AMOUNT);
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("id"))
                        .nickname(resultSet.getString("nickname"))
                        .password(resultSet.getString("password"))
                        .birthday(resultSet.getDate("birthday"))
                        .amount(resultSet.getInt("amount"))
                        .build();
                AppData.getInstance().setUser(user);
            }
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
    public void addGame(int gameId, int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_GAME_TO_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();
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
