package CreationalDesignPattern;

import java.util.HashMap;
import java.util.Map;
/**Prototype pattern creates new objects by cloning existing ones,
reducing costly object creation and allowing dynamic object generation at runtime.**/

interface Shape extends Cloneable {
    Shape clone();

    void draw();
}

class Circle implements Shape {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this.radius);
    }

    @Override
    public void draw() {
        System.out.println("Radius of a Circle:" + this.radius);
    }
}

class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this.width, this.height);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle with width: " + width + " and height: " + height);
    }
}

class Square implements Shape {
    private int width;
    private int height;

    public Square(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape clone() {
        return new Square(this.width, this.height);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Square with width: " + width + " and height: " + height);
    }
}

class ShapeRegistry {
    private Map<String, Shape> prototypes = new HashMap<>();

    public void addPrototype(String key, Shape prototype) {
        prototypes.put(key, prototype);
    }

    public Shape getClone(String key) {
        Shape prototype = prototypes.get(key);
        return prototype != null ? prototype.clone() : null;
    }
}

class PrototypePattern {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        Rectangle rectangle = new Rectangle(56, 90);
        Square square = new Square(32, 39);

        ShapeRegistry registry = new ShapeRegistry();
        registry.addPrototype("big circle", circle);
        registry.addPrototype("small rectangle", rectangle);
        registry.addPrototype("big square", square);

        Shape clonedCircle = registry.getClone("big circle");
        Shape clonedRectangle = registry.getClone("small rectangle");
        Shape clonedSquare = registry.getClone("big square");

        clonedCircle.draw();
        clonedRectangle.draw();
        clonedSquare.draw();
    }
}

