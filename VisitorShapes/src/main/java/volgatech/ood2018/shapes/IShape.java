package volgatech.ood2018.shapes;

import volgatech.ood2018.bignumber.BigNumber;

public interface IShape {
    void areaCalculation();
    void perimeterCalculation();
    BigNumber getArea();
    BigNumber getPerimeter();
    String getName();
}
