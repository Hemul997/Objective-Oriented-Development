package volgatech.ood2018.visitors;

import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.Rectangle;
import volgatech.ood2018.shapes.Triangle;

public interface IShapeVisitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
    void visit(Triangle triangle);
}
