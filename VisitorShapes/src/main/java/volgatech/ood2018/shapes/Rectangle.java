package volgatech.ood2018.shapes;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.elements.Dot;
import volgatech.ood2018.visitors.IShapeVisitor;

public class Rectangle implements IShape {

    private Dot firstVertex, secondVertex;
    private BigNumber height, length;
    private BigNumber area, perimeter;

    public Rectangle(BigNumber x1, BigNumber y1, BigNumber x2, BigNumber y2) {
        this.firstVertex = new Dot(x1, y1);
        this.secondVertex = new Dot(x2, y2);
        this.height = secondVertex.getY().subtract(firstVertex.getY());
        this.length = secondVertex.getX().subtract(firstVertex.getX());
        areaCalculation();
        perimeterCalculation();
    }

    public void areaCalculation() {
        this.area = this.height.multiply(this.length);
    }

    public void perimeterCalculation() {
        this.perimeter = (this.length.add(this.height)).multiply(new BigNumber("2"));
    }

    public BigNumber getArea() {
        return area;
    }

    public BigNumber getPerimeter() {
        return perimeter;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public String accept(IShapeVisitor visitor) {
        return visitor.visit(this);
    }
}
