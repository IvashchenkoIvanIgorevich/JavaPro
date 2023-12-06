package org.example.service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Shelter;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PetSerializer {

    private static final String PATH = "petShelter/src/main/resources/shelter";

    private final ObjectMapper mapper;
    private final String path;

    public PetSerializer(ObjectMapper mapper, Format format) {
        this.mapper = mapper;
        this.path = PATH + format;
    }

    public void serialize(Shelter shelter) {
        try {
          mapper.writeValue(new File(path), shelter);
        } catch (IOException e) {
            System.err.println("Can't create file! Try one more time.");
        }
    }

    public Optional<Shelter> deserialize() {
        try {
            File file = new File(path);
            if (file.exists()) {
                return Optional.of(mapper.readValue(file, Shelter.class));
            }
            return Optional.empty();
        } catch (NullPointerException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException("Can't get data from the file (" + path + "). File was not found or corrupted.");
        }
    }
}
