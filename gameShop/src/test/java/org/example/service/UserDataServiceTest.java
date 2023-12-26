package org.example.service;

import org.example.AppData;
import org.example.model.Game;
import org.example.model.User;
import org.example.service.mock.GameRepositoryMock;
import org.example.service.mock.UserRepositoryMock;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDataServiceTest {

    private UserDataService userDataService;

    @Before
    public void setUp() {
        List<User> users = List.of(
                User.builder().id(1).nickname("user1").password("111").amount(10).build(),
                User.builder().id(2).nickname("user2").password("222").amount(0).build(),
                User.builder().id(3).nickname("user3").password("333").amount(40).build()
        );
        UserRepositoryMock userRepositoryMock = new UserRepositoryMock(users);

        List<Game> games = List.of(
                Game.builder().id(1).name("game1").rating(1).cost(10).build(),
                Game.builder().id(2).name("game2").rating(4).cost(30).build(),
                Game.builder().id(3).name("game3").rating(5).cost(40).build(),
                Game.builder().id(4).name("game4").rating(6).cost(50).build()
        );
        GameRepositoryMock gameRepositoryMock = new GameRepositoryMock(games);

        userDataService = new UserDataService(userRepositoryMock, gameRepositoryMock);
    }

    @Test
    public void testGetAllUserGames() {
        User currentUser = User.builder().id(1).nickname("nickname").build();
        AppData.getInstance().setUser(currentUser);

        List<Game> allUserGames = userDataService.getAllUserGames();

        int expected = 2;
        assertEquals(expected, allUserGames.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllUserGamesUnauthorizedUserShouldThrowIllegalArgumentException() {
        AppData.getInstance().setUser(null);

        userDataService.getAllUserGames();
    }

    @Test
    public void testAddGame() {
        User currentUser = User.builder().id(1).nickname("user1").password("111").amount(100).build();
        AppData.getInstance().setUser(currentUser);

        userDataService.addGame(2);

        int expectedAmount = 60;
        assertEquals(expectedAmount, currentUser.getAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addGameEnoughMoneyShouldThrowIllegalArgumentException() {
        User currentUser = User.builder().id(1).nickname("nickname").amount(0).build();
        AppData.getInstance().setUser(currentUser);

        userDataService.addGame(2);
    }
}