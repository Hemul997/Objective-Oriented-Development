package volgatech.ood2018.visitors;

import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.IShape;
import volgatech.ood2018.shapes.Rectangle;
import volgatech.ood2018.shapes.Triangle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PrintVisitor implements IShapeVisitor{

    private StringBuilder builder = new StringBuilder("");


    public void print(FileWriter writer, IShape shape) throws IOException{
        shape.accept(this);
        writer.write(builder.toString());
    }

    @Override
    public void visit(Circle circle) {
        builder.append(circle.getName() + ':'
                + " Perimeter: " + circle.getPerimeter().toString()
                + " Area: " + circle.getArea().toString() + '\n');

    }

    @Override
    public void visit(Rectangle rectangle) {
        builder.append(rectangle.getName() + ':'
                + " Perimeter: " + rectangle.getPerimeter().toString()
                + " Area: " + rectangle.getArea().toString() + '\n');
    }

    @Override
    public void visit(Triangle triangle) {
        builder.append(triangle.getName() + ':'
                + " Perimeter: " + triangle.getPerimeter().toString()
                + " Area: " + triangle.getArea().toString() + '\n');
    }

}
