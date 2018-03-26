package volgatech.ood2018.visitors;

import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.Rectangle;
import volgatech.ood2018.shapes.Triangle;

public interface IShapeVisitor {
    String visit(Circle circle);
    String visit(Rectangle rectangle);
    String visit(Triangle triangle);
}
