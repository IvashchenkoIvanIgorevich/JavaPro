package org.example.service.menu;

import org.example.Executor;
import org.example.service.UserService;

public class AuthorizationMenuService {

    private final UserService userService;
    private final ShopMenuService shopMenuService;

    public AuthorizationMenuService(UserService userService, ShopMenuService shopMenuService) {
        this.userService = userService;
        this.shopMenuService = shopMenuService;
    }

    public Executor signUp() {
        return () -> userService.signUp(shopMenuService.registration());
    }

    public Executor signIn() {
        return () -> userService.signIn(shopMenuService.getNickname(), shopMenuService.getPassword());
    }

    public Executor exit() {
        return () -> System.out.println("Have a nice day");
    }
}
