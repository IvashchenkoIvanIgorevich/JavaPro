package org.example.service;

import org.example.AppData;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(User user) {
        User currentUser = userRepository.save(user);
        if (currentUser == null) {
            throw new IllegalArgumentException("Can't create user");
        }
    }

    public void signIn(String nickname, String password) {
        User user = userRepository.get(nickname, password);
        if (user == null) {
            throw new IllegalArgumentException("Can't find user");
        }

        authorize(user);
    }

    private static void authorize(User user) {
        User currectUser = AppData.getInstance().getUser();
        if (currectUser == null || currectUser.getId() != user.getId()) {
            AppData.getInstance().setUser(user);
        }
    }

    public void makeDeposit(int amount) {
        User currentUser = AppData.getInstance().getUser();
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found. You should sing in.");
        }
        int result = amount + currentUser.getAmount();
        userRepository.updateDeposit(currentUser.getId(), result);
    }
}
