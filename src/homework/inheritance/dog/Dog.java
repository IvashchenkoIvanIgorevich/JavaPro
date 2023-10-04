package homework.inheritance.dog;

import homework.inheritance.Animal;
import homework.inheritance.Running;
import homework.inheritance.Swimming;

public class Dog extends Animal implements Running, Swimming {

    private final int runLimit;
    private final int swimLimit;

    public Dog(String name, int runLimit, int swimLimit) {
        super(name);
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
    }

    @Override
    public boolean run(int distance) {
        return distance <= runLimit;
    }

    @Override
    public boolean swim(int distance) {
        return distance <= swimLimit;
    }
}
