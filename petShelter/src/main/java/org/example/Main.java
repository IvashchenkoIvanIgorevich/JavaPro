package org.example;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Pet;
import org.example.model.Shelter;
import org.example.service.PetService;
import org.example.service.PetServiceImp;
import org.example.service.serializer.Format;
import org.example.service.serializer.PetSerializer;
import org.example.view.Command;
import org.example.view.Menu;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PetSerializer petSerializer = new PetSerializer(new JsonMapper(), Format.JSON);
        Shelter shelter = new Shelter();

        try {
            Optional<Shelter> shelterData = petSerializer.deserialize();
            if (shelterData.isPresent()) {
                shelter = shelterData.get();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        boolean exit = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
                try {
                    Menu.showMenu();

                    String input = scanner.next();
                    if (Command.EXIT.getAction().equals(input)) {
                        exit = true;
                        petSerializer.serialize(shelter);
                        continue;
                    }

                    PetService service = new PetServiceImp(shelter.getPets());

                    if (Command.ADD.getAction().equals(input)) {
                        Pet newPet = Menu.createPet(scanner);
                        service.add(newPet);
                    }

                    if (Command.DELETE.getAction().equals(input)) {
                        String key = Menu.selectPetKey(scanner);
                        service.delete(key);
                    }

                    if (Command.SHOW.getAction().equals(input)) {
                        Menu.showPets(service.getAll());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
