package org.example;

import org.example.service.*;
import org.example.service.menu.AuthorizationMenuService;
import org.example.service.menu.ShopMenuService;
import org.example.service.menu.UserMenuService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            scanner.useDelimiter("\n");
            Singleton singleton = Singleton.getInstance(ConnectionSingleton.getConnection());

            ShopMenuService shopMenuService = new ShopMenuService(scanner);
            Map<String, Executor> orchestrator = initMainMenuCommand(singleton.getUserService(), shopMenuService);

            String command;
            boolean authorized = false;
            do {
                if (AppData.getInstance().getUser() != null) {
                    shopMenuService.showUserMenu();
                    if (!authorized) {
                        orchestrator = initUserMenuCommand(singleton.getUserDataService(), singleton.getUserService(),
                                singleton.getGameService(), shopMenuService);
                        authorized = true;
                    }
                } else {
                    shopMenuService.showMainMenu();
                    authorized = false;
                    orchestrator = initMainMenuCommand(singleton.getUserService(), shopMenuService);
                }

                command = scanner.next();

                try {
                    orchestrator
                            .getOrDefault(command, () -> System.out.println("Incorrect command"))
                            .execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } while (!command.equals(Command.EXIT.getKey()));

        } catch (Exception e) {
            System.out.println("Error. Please contact with support team.");
        }
    }

    private static Map<String, Executor> initMainMenuCommand(UserService userService, ShopMenuService shopMenuService) {
        AuthorizationMenuService authorizationMenuService = new AuthorizationMenuService(userService, shopMenuService);
        Map<String, Executor> commands = new HashMap<>();
        commands.put(Command.SIGN_UP.getKey(), authorizationMenuService.signUp());
        commands.put(Command.SIGN_IN.getKey(), authorizationMenuService.signIn());
        commands.put(Command.EXIT.getKey(), authorizationMenuService.exit());
        return commands;
    }

    private static Map<String, Executor> initUserMenuCommand(UserDataService userDataService, UserService userService,
                                                             GameService gameService, ShopMenuService shopMenuService) {
        UserMenuService userMenuService = new UserMenuService(userDataService, userService, gameService, shopMenuService);
        Map<String, Executor> commands = new HashMap<>();
        commands.put(Command.SHOW_ALL_USER_GAMES.getKey(), userMenuService.showAllUserGames());
        commands.put(Command.SHOW_ALL_GAMES.getKey(), userMenuService.showAllGames());
        commands.put(Command.DEPOSIT.getKey(), userMenuService.makeDeposit());
        commands.put(Command.BUY_GAME.getKey(), userMenuService.addGame());
        commands.put(Command.LOGOUT.getKey(), userMenuService.logout());
        return commands;
    }
}
