package org.example.service.serializer;

public enum Format {

    JSON(".json");

    private final String extension;

    Format(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return extension;
    }
}
