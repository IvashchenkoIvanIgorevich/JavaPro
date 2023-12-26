package org.example.repository.dao;

import org.example.model.User;

import java.util.List;

public interface UserRepository {

    User get(String nickname, String password);

    User save(User user);

    List<Integer> getUserGameIds(int userId);

    void updateDeposit(int userId, int amount);

    void addGame(int gameId, int userId);
}
