package org.example.service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.DeserializationException;
import org.example.exception.SerializationException;
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

    public void serialize(Shelter shelter) throws SerializationException {
        try {
          mapper.writeValue(new File(path), shelter);
        } catch (IOException e) {
            throw new SerializationException("Can't create file! Try one more time.", e);
        }
    }

    public Optional<Shelter> deserialize() throws DeserializationException {
        try {
            File file = new File(path);
            if (file.exists()) {
                return Optional.of(mapper.readValue(file, Shelter.class));
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new DeserializationException("Can't get data from the file (" + path + "). File was not found or corrupted.", e);
        }
    }
}
