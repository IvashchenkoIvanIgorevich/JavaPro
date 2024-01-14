package org.example.repository.mapper;

import org.example.model.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper {

    private static final String FLD_ID = "id";
    private static final String FLD_NAME = "name";
    private static final String FLD_DESCRIPTION = "description";
    private static final String FLD_RATING = "rating";
    private static final String FLD_COST = "cost";
    private static final String FLD_RELEASE = "release";

    public static Game mapResultSetToGame(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getInt(FLD_ID))
                .name(resultSet.getString(FLD_NAME))
                .description(resultSet.getString(FLD_DESCRIPTION))
                .rating(resultSet.getInt(FLD_RATING))
                .cost(resultSet.getInt(FLD_COST))
                .release(resultSet.getDate(FLD_RELEASE))
                .build();
    }
}
