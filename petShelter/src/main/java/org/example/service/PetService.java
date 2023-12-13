package org.example.service;

import org.example.model.Pet;

import java.util.Map;

public interface PetService {

    void add(Pet pet);

    void delete(String key);

    Map<String, Pet> getAll();

    String getKeyByPet(Pet pet);
}
