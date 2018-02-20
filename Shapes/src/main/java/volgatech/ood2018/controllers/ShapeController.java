package volgatech.ood2018.controllers;

import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.IShape;
import volgatech.ood2018.shapes.Rectangle;
import volgatech.ood2018.shapes.Triangle;

import java.io.FileWriter;
import java.io.IOException;

public class ShapeController {

    public void printShapeData(IShape shape, FileWriter writer) throws IOException {
        writer.write(shape.getName() + ':');
        writer.write(" Perimeter: " + shape.getPerimeter());
        writer.write(" Area: " + shape.getArea() + '\n');
    }

    public IShape makeShape(String line) {
        IShape shape = null;
        String[] shapeParams = line.split(" ");
        switch (shapeParams[0]) {
            case "Triangle":
                shape = new Triangle(Integer.parseInt(shapeParams[1]), Integer.parseInt(shapeParams[2]),
                    Integer.parseInt(shapeParams[3]), Integer.parseInt(shapeParams[4]), Integer.parseInt(shapeParams[5]),
                    Integer.parseInt(shapeParams[6]));
                break;
            case "Circle":
                shape = new Circle(Integer.parseInt(shapeParams[1]), Integer.parseInt(shapeParams[2]),
                    Integer.parseInt(shapeParams[3]));
                break;
            case "Rectangle":
                shape = new Rectangle(Integer.parseInt(shapeParams[1]), Integer.parseInt(shapeParams[2]),
                        Integer.parseInt(shapeParams[3]), Integer.parseInt(shapeParams[4]));
                break;
            default:
                break;
        }
        return shape;
    }
}
