package homework;

import homework.inheritance.Animal;
import homework.inheritance.Running;
import homework.inheritance.Swimming;
import homework.polymorphism.competition.Competition;
import homework.polymorphism.competition.obstacle.Obstacle;
import homework.polymorphism.competition.obstacle.Racetrack;
import homework.polymorphism.competition.obstacle.Wall;
import homework.polymorphism.competition.participant.Cat;
import homework.polymorphism.competition.participant.Human;
import homework.polymorphism.competition.participant.Participant;
import homework.polymorphism.competition.participant.Robot;
import homework.polymorphism.geometricshape.AreaCalculable;
import homework.polymorphism.geometricshape.model.Circle;
import homework.polymorphism.geometricshape.model.Square;
import homework.polymorphism.geometricshape.model.Triangle;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Verifier {

    private static void calculateArea() {
        Circle circle = new Circle(3.456);
        Square square = new Square(4.234);
        Triangle triangle = new Triangle(23.123, 5.78);

        List<AreaCalculable> figures = Arrays.asList(circle, square, triangle);
        double sumAreaFigures = figures.stream().mapToDouble(AreaCalculable::calculateArea).sum();
        System.out.println("The total area of all geometric figures: " + sumAreaFigures);
    }

    private static void doCompetition() {
        Human human = new Human("Harry Potter", 1200, 40);
        Cat cat = new Cat("Minerva McGonagall", 300, 300);
        Robot robot = new Robot("C-3PO", 1000, 1200);

        Racetrack racetrack = new Racetrack("Forbidden Forest", 1000);
        Wall wall = new Wall("Hogwarts", 300);

        List<Participant> participants = Arrays.asList(human, cat, robot);
        List<Obstacle> obstacles = Arrays.asList(racetrack, wall);

        Competition competition = new Competition(participants, obstacles);
        Map<Participant, List<Obstacle>> result = competition.start();

        result.forEach((participant, challenges) -> {
            challenges.forEach(obstacle -> {
                StringBuilder report = new StringBuilder()
                        .append("Participant ")
                        .append(participant.getName())
                        .append(" ");

                if (obstacle.isOvercome()) {
                    report.append("passed the obstacle ");
                } else {
                    report.append("did not pass the obstacle ");
                }

                report.append(obstacle.getName())
                        .append(" at the distance ")
                        .append(obstacle.getLength());

                if (!obstacle.isOvercome()) {
                    report.append(". Passed ")
                            .append(participant.getPassedDistance());
                }

                System.out.println(report);
            });
        });
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
