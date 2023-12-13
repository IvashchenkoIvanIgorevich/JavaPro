package org.example.view;

import org.example.model.Pet;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    public static void showMenu() {
        System.out.println("Menu");
        Arrays.stream(Command.values()).forEachOrdered(c -> System.out.println(c.getAction() + " - " + c));
    }

    public static void showPets(Map<String, Pet> pets) {
        if (pets != null && !pets.isEmpty()) {
            for (String key : pets.keySet()) {
                System.out.println(key + " - " + pets.get(key));
            }
        }
    }

    public static Pet createPet(Scanner scanner) {
        System.out.print("Please enter pet's name:");
        String name = scanner.next();

        System.out.print("Please enter pet's breed:");
        String breed = scanner.next();

        boolean correctAge = false;
        int age = 0;
        do {
            System.out.print("Please enter pet's full age:");
            try {
                age = Integer.parseUnsignedInt(scanner.next());
                correctAge = true;
            } catch (NumberFormatException e) {
                System.out.println("Age should be positive decimal value.");
            }
        } while (!correctAge);

        return new Pet(name, breed, age);
    }

    public static String selectPetKey(Scanner scanner) {
        System.out.print("Please select the index number of the pet from the list that you would like to remove:");
        return scanner.next();
    }
}
