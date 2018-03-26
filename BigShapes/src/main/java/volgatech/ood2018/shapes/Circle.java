package volgatech.ood2018.shapes;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.elements.Dot;

public class Circle implements IShape {
    final private BigNumber PI = new BigNumber("314");
    final private BigNumber PI_DIVIDER = new BigNumber("100");

    private Dot center;
    private BigNumber radius;
    private BigNumber area, perimeter;


    public Circle(BigNumber x1, BigNumber y1, BigNumber radius) {
        this.center = new Dot(x1, y1);
        this.radius = radius;
        areaCalculation();
        perimeterCalculation();
    }

    public BigNumber getArea() {
        return area;
    }

    public BigNumber getPerimeter() {
        return perimeter;
    }

    @Override
    public String getName() {
        return "Circle";

    }

    public void areaCalculation() {
        try {
            this.area = (PI.multiply(this.radius.pow(2))).divide(PI_DIVIDER);
        } catch (Exception e) {
            this.area = new BigNumber("0");
        }
    }

    public void perimeterCalculation() {
        try {
            this.perimeter = (PI.multiply(this.radius)).multiply(new BigNumber("2")).divide(PI_DIVIDER);
        } catch (Exception e) {
            this.perimeter = new BigNumber("0");
        }
    }
}
