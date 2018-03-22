package volgatech.ood2018.controllers;
import volgatech.ood2018.creators.CirclesCreator;
import volgatech.ood2018.creators.IShapesCreator;
import volgatech.ood2018.creators.RectanglesCreator;
import volgatech.ood2018.creators.TrianglesCreator;
import volgatech.ood2018.shapes.IShape;

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
        IShapesCreator shapesCreator;
        String[] params = line.split(" ");
        String shapeParams = line.substring(params[0].length() + 1);
        switch (params[0]) {
            case "Triangle":
                shapesCreator = TrianglesCreator.getInstance();
                shape = shapesCreator.create(shapeParams);
                break;
            case "Circle":
                shapesCreator = CirclesCreator.getInstance();
                shape = shapesCreator.create(shapeParams);
                break;
            case "Rectangle":
                shapesCreator = RectanglesCreator.getInstance();
                shape = shapesCreator.create(shapeParams);
                break;
        }
        return shape;
    }
}
