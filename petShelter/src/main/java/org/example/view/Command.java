package org.example.view;

import lombok.Getter;

public enum Command {

    ADD("1", "Add pet"),
    DELETE("2", "Delete pet"),
    SHOW("3", "Show all pets"),
    EXIT("4", "Exit");

    private final String text;

    @Getter
    private final String action;

    Command(String action, String text) {
        this.text = text;
        this.action = action;
    }

    @Override
    public String toString() {
        return text;
    }
}
