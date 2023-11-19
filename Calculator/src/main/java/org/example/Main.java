package org.example;

public class Main {

    public static void main(String[] args) {

        try {
            double firstArg = Double.parseDouble(args[0]);
            double secondArg = Double.parseDouble(args[1]);
            String operation = args[2];

            double result = Calculator.doCalculation(firstArg, secondArg, Operation.fromString(operation));
            System.out.println(firstArg + operation + secondArg + " = " + result);

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage() + ". Argument should be a number.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
