package homework.inheritance.polymorphism.geometricshape.model;

import homework.inheritance.polymorphism.geometricshape.AreaCalculable;

public class Triangle implements AreaCalculable {

    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double calculateArea() {
        return (base * height) / 2;
    }
}
