package org.example.service.menu;

import org.example.AppData;
import org.example.Executor;
import org.example.service.GameService;
import org.example.service.UserDataService;
import org.example.service.UserService;

public class UserMenuService {

    private final UserDataService userDataService;
    private final ShopMenuService shopMenuService;
    private final UserService userService;
    private final GameService gameService;

    public UserMenuService(UserDataService userDataService, UserService userService, GameService gameService,
                           ShopMenuService shopMenuService) {
        this.userDataService = userDataService;
        this.shopMenuService = shopMenuService;
        this.userService = userService;
        this.gameService = gameService;
    }

    public Executor showAllUserGames() {
        return () -> userDataService.getAllUserGames().forEach(g -> System.out.println(g.toString()));
    }

    public Executor logout() {
        return () -> AppData.getInstance().setUser(null);
    }

    public Executor makeDeposit() {
        return () -> userService.makeDeposit(Integer.parseInt(shopMenuService.getAmount()));
    }

    public Executor addGame() {
        return () -> userDataService.addGame(Integer.parseInt(shopMenuService.getGameId()));
    }

    public Executor showAllGames() {
        return () -> gameService.getAllGames().forEach(g -> System.out.println(g.toString()));
    }
}
