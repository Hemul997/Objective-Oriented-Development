package volgatech.ood2018.controllers;
import volgatech.ood2018.creators.CirclesCreator;
import volgatech.ood2018.creators.IShapesCreator;
import volgatech.ood2018.creators.RectanglesCreator;
import volgatech.ood2018.creators.TrianglesCreator;
import volgatech.ood2018.shapes.IShape;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class ShapeController {

    private String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }

    public void printShapeData(IShape shape, FileWriter writer) throws IOException {
        writer.write(shape.getName() + ':');
        writer.write(" Perimeter: " + customFormat("###,###.###", shape.getPerimeter()));
        writer.write(" Area: " + customFormat("###,###.###", shape.getArea()) + '\n');
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
