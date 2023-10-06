import homework.inheritance.Animal;
import homework.inheritance.Running;
import homework.inheritance.Swimming;
import homework.inheritance.polymorphism.geometricshape.AreaCalculable;
import homework.inheritance.polymorphism.geometricshape.model.Circle;
import homework.inheritance.polymorphism.geometricshape.model.Square;
import homework.inheritance.polymorphism.geometricshape.model.Triangle;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Calculating area of figures
        Circle circle = new Circle(3.456);
        Square square = new Square(4.234);
        Triangle triangle = new Triangle(23.123, 5.78);

        List<AreaCalculable> figures = Arrays.asList(circle, square, triangle);
        double sumAreaFigures = figures.stream().mapToDouble(AreaCalculable::calculateArea).sum();
        System.out.println("The total area of all geometric figures: " + sumAreaFigures);
    }

    private static void runningCompetition(List<Animal> animals, int distance) {
        for (Animal animal : animals) {
            if (animal instanceof Running) {
                Running runningAnimal = (Running) animal;
                boolean mileage = runningAnimal.run(distance);
                StringBuilder constructedResult = new StringBuilder()
                        .append(animal.getName())
                        .append(mileage ? " ran " : " couldn't run ")
                        .append(distance)
                        .append(" m.");
                System.out.println(constructedResult);
            } else {
                System.out.println(animal.getName() + " can't run.");
            }
        }
    }

    private static void swimmingCompetition(List<Animal> animals, int distance) {
        for (Animal animal : animals) {
            if (animal instanceof Swimming) {
                Swimming swimmingAnimal = (Swimming) animal;
                boolean mileage = swimmingAnimal.swim(distance);
                StringBuilder constructedResult = new StringBuilder()
                        .append(animal.getName())
                        .append(mileage ? " swam " : " couldn't swim ")
                        .append(distance)
                        .append(" m.");
                System.out.println(constructedResult);
            } else {
                System.out.println(animal.getName() + " can't swim.");
            }
        }
    }
}
