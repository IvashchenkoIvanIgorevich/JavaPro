package org.example.service.menu;

import org.example.AppData;
import org.example.Command;
import org.example.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ShopMenuService {

    private final Scanner scanner;

    public ShopMenuService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMainMenu() {
        System.out.println("Welcome to our secret shop!\n -> " + String.join("\n -> ", Command.getMainMenuCommands()));
    }

    public void showUserMenu() {
        System.out.println("Welcome " + AppData.getInstance().getUser() + " to our secret shop!\n -> " +
                          String.join("\n -> ", Command.getUserMenuCommands()));
    }

    public User registration() {
        User.UserBuilder builder = User.builder();
        System.out.println("Please enter your data!");
        System.out.print("Nickname: ");
        builder.nickname(scanner.next());
        System.out.print("Password: ");
        builder.password(scanner.next());
        Date dateOfBirthday = parseDate();
        builder.birthday(dateOfBirthday);
        return builder.build();
    }

    private Date parseDate() {
        boolean exit = false;
        Date dateOfBirthday = null;
        while (!exit) {
            System.out.print("DOB: ");
            try {
                String command = scanner.next();
                if (Command.EXIT.getMessage().equals(command)) {
                    break;
                }
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                dateOfBirthday = dateFormat.parse(command);
                exit = true;
            } catch (ParseException e) {
                System.out.println(e.getMessage() + " Date format should be MM/DD/YYYY");
            }
        }
        return dateOfBirthday;
    }

    public String getNickname() {
        System.out.println("Please enter your nickname!");
        System.out.print("Nickname: ");
        return scanner.next();
    }

    public String getPassword() {
        System.out.println("Please enter your password!");
        System.out.print("Password: ");
        return scanner.next();
    }

    public String getAmount() {
        System.out.println("Please enter deposit!");
        System.out.print("Deposit: ");
        return scanner.next();
    }

    public String getGameId() {
        System.out.println("Please enter game id!");
        System.out.print("Game id: ");
        return scanner.next();
    }
}
