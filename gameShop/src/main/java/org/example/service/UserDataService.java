package org.example.service;

import org.example.AppData;
import org.example.model.Game;
import org.example.model.User;
import org.example.repository.dao.GameRepository;
import org.example.repository.dao.UserRepository;

import java.util.List;

public class UserDataService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserDataService(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllUserGames() {
        User currentUser = AppData.getInstance().getUser();
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found. You should sing in.");
        }

        List<Integer> currentUserGameIds = userRepository.getUserGameIds(currentUser.getId());

        return gameRepository.getGamesByIds(currentUserGameIds);
    }

    public void addGame(int gameId) {
        User currentUser = AppData.getInstance().getUser();
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found. You should sing in.");
        }

        Game game = gameRepository.getGameById(gameId);
        if (game.getCost() > currentUser.getAmount()) {
            throw new IllegalArgumentException("Not enough money. Please make deposit.");
        }

        userRepository.addGame(gameId, currentUser.getId());

        int updatedAmount = currentUser.getAmount() - game.getCost();
        userRepository.updateDeposit(currentUser.getId(), updatedAmount);

        User user = userRepository.get(currentUser.getNickname(), currentUser.getPassword());

        AppData.getInstance().setUser(user);
    }
}
