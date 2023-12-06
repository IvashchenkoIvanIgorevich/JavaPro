package org.example.service;

import org.example.model.Pet;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PetServiceImpTest {

    private final PetServiceImp petServiceImp = new PetServiceImp(new HashMap<>());
    @Test
    public void addTest() {
        Pet pet = new Pet("name", "breed", 1);

        petServiceImp.add(pet);

        assertFalse(petServiceImp.getAll().isEmpty());
    }

    @Test
    public void deleteTest() {
        Pet petFirst = new Pet("nameFirst", "breedFirst", 1);
        Pet petSecond = new Pet("nameSecond", "breedSecond", 1);
        petServiceImp.add(petFirst);
        petServiceImp.add(petSecond);
        String key = petServiceImp.getKeyByPet(petSecond);

        petServiceImp.delete(key);

        assertEquals(1, petServiceImp.getAll().size());
        assertFalse(petServiceImp.getAll().containsKey(key));
    }

    @Test
    public void getAllTest() {
        Pet pet = new Pet("name", "breed", 1);
        petServiceImp.add(pet);

        int expected = 1;

        Map<String, Pet> pets = petServiceImp.getAll();
        int actual = pets.size();

        assertEquals(expected, actual);
    }
}