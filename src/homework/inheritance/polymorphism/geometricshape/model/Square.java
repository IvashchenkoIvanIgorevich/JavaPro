package homework.inheritance.polymorphism.geometricshape.model;

import homework.inheritance.polymorphism.geometricshape.AreaCalculable;

public class Square implements AreaCalculable {

    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}
