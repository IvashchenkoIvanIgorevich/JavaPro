package homework.polymorphism.geometricshape.model;

import homework.polymorphism.geometricshape.AreaCalculable;

public class Circle implements AreaCalculable {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
