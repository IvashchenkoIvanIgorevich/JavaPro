package org.example.service.serializer;

public enum Format {

    JSON(".json"),
    XML(".xml"),
    YAML(".yaml");

    private final String extension;

    Format(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return extension;
    }
}
