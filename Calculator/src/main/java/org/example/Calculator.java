package org.example;

public class Calculator {

    public static double doCalculation(double firstArgument, double secondArgument, Operation operation) {
        switch (operation) {
            case ADDITION:
                return firstArgument + secondArgument;
            case SUBTRACTION:
                return firstArgument - secondArgument;
            case MULTIPLICATION:
                return firstArgument * secondArgument;
            case DIVISION:
                return firstArgument / secondArgument;
            default:
                throw new IllegalArgumentException("Operation " + operation + " has not supported yet.");
        }
    }
}
