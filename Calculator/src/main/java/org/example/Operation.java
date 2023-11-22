package org.example;

public enum Operation {

    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("x"),
    DIVISION("/");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public static Operation fromString(String text) {
        for (Operation operation : Operation.values()) {
            if (operation.symbol.equals(text)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("No operation with symbol " + text + " found");
    }
}
