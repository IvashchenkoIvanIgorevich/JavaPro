import homework.inheritance.Animal;
import homework.inheritance.Running;
import homework.inheritance.Swimming;
import homework.inheritance.cat.Cat;
import homework.inheritance.dog.Dog;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dog bobik = new Dog("Bobik", 510, 10);
        Dog sharik = new Dog("Sharik", 600, 9);
        Dog black = new Dog("Black", 300, 15);

        Cat jack = new Cat("Jack", 210);
        Cat john = new Cat("John", 300);
        Cat pushok = new Cat("Pushok", 100);

        List<Animal> animals = Arrays.asList(bobik, sharik, black, jack, john, pushok);

        runningCompetition(animals, 500);
        swimmingCompetition(animals, 10);
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
