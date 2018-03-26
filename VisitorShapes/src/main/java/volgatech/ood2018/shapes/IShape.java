package volgatech.ood2018.shapes;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.visitors.IShapeVisitor;

public interface IShape {
    void areaCalculation();
    void perimeterCalculation();
    BigNumber getArea();
    BigNumber getPerimeter();
    String getName();
    String accept(IShapeVisitor visitor);
}
