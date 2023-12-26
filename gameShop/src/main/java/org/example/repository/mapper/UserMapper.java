package org.example.repository.mapper;

import org.example.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private static final String FLD_ID = "id";
    private static final String FLD_NICKNAME = "nickname";
    private static final String FLD_PASSWORD = "password";
    private static final String FLD_BIRTHDAY = "birthday";
    private static final String FLD_AMOUNT = "amount";

    public static User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt(FLD_ID))
                .nickname(resultSet.getString(FLD_NICKNAME))
                .password(resultSet.getString(FLD_PASSWORD))
                .birthday(resultSet.getDate(FLD_BIRTHDAY))
                .amount(resultSet.getInt(FLD_AMOUNT))
                .build();
    }
}
