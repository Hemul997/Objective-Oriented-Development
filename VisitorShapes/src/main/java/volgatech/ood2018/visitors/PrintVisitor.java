package volgatech.ood2018.visitors;

import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.IShape;
import volgatech.ood2018.shapes.Rectangle;
import volgatech.ood2018.shapes.Triangle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PrintVisitor implements IShapeVisitor{

    public void print(FileWriter writer, IShape shape) throws IOException{
        writer.write(shape.accept(this));
    }

    @Override
    public String visit(Circle circle) {
        return (circle.getName() + ':'
                + " Perimeter: " + circle.getPerimeter().toString()
                + " Area: " + circle.getArea().toString() + '\n');

    }

    @Override
    public String visit(Rectangle rectangle) {
        return (rectangle.getName() + ':'
                + " Perimeter: " + rectangle.getPerimeter().toString()
                + " Area: " + rectangle.getArea().toString() + '\n');
    }

    @Override
    public String visit(Triangle triangle) {
        return (triangle.getName() + ':'
                + " Perimeter: " + triangle.getPerimeter().toString()
                + " Area: " + triangle.getArea().toString() + '\n');
    }

}
