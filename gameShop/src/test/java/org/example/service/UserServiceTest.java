package org.example.service;

import org.example.AppData;
import org.example.model.User;
import org.example.repository.dao.UserRepository;
import org.example.service.mock.UserRepositoryMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setUp() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1).nickname("user1").password("111").amount(10).build());
        users.add(User.builder().id(2).nickname("user2").password("222").amount(0).build());
        users.add(User.builder().id(3).nickname("user3").password("333").amount(40).build());
        userRepository = new UserRepositoryMock(users);

        userService = new UserService(userRepository);
    }

    @Test
    public void testSignUp() {
        User newUser = User.builder().id(4).nickname("newUser").password("123").amount(11).build();

        userService.signUp(newUser);

        User user = userRepository.get(newUser.getNickname(), newUser.getPassword());
        assertEquals(newUser.getId(), user.getId());
        assertEquals(newUser.getNickname(), user.getNickname());
    }

    @Test
    public void testSignIn() {
        String nickname = "user1";
        String password = "111";

        userService.signIn(nickname, password);

        User user = AppData.getInstance().getUser();
        int expectedUserId = 1;
        assertEquals(nickname, user.getNickname());
        assertEquals(expectedUserId, user.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void signInIncorrectUserCredentialShouldThrowIllegalArgumentException() {
        String nickname = "user";
        String password = "1112";

        userRepository = new UserRepositoryMock(new ArrayList<>()) {
            @Override
            public User get(String nickname, String password) {
                return null;
            }
        };

        userService = new UserService(userRepository);

        userService.signIn(nickname, password);
    }
}