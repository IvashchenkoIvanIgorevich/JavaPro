package org.example.service.mock;

import org.example.AppData;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.util.List;

public class UserRepositoryMock implements UserRepository {

    private List<User> users;

    public UserRepositoryMock(List<User> users) {
        this.users = users;
    }

    @Override
    public User get(String nickname, String password) {
        return users.stream()
                .filter(u -> u.getNickname().equals(nickname) && u.getPassword().equals(password))
                .findAny()
                .get();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<Integer> getUserGameIds(int userId) {
        return List.of(1,3);
    }

    @Override
    public void updateDeposit(int userId, int amount) {
        User user = AppData.getInstance().getUser();
        user.setAmount(amount);
    }

    @Override
    public void addGame(int gameId, int userId) {
    }
}
