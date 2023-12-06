package org.example.service.serializer;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Pet;
import org.example.model.Shelter;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PetSerializerTest {

    private final PetSerializer petSerializer = new PetSerializer(new JsonMapper(), Format.JSON);
    private Shelter shelter;

    @Before
    public void setUp() {
        shelter = new Shelter();
        Map<String, Pet> pets = new HashMap<>();
        pets.put("1", new Pet("nameFirst","breedFirst",1));
        pets.put("2", new Pet("nameSecond","breedSecond",3));

        shelter.setPets(pets);
    }

    @Test
    public void serializeTest() {
        petSerializer.serialize(shelter);
    }

    @Test
    public void deserializeTest() {
        Optional<Shelter> deserialize = petSerializer.deserialize();
        assertEquals(shelter, deserialize.orElse(null));
    }
}