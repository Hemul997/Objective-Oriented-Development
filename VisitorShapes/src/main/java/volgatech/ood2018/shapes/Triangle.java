package volgatech.ood2018.shapes;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.elements.Vector;
import volgatech.ood2018.shapes.elements.Dot;
import volgatech.ood2018.visitors.IShapeVisitor;

public class Triangle implements IShape {
    private Dot firstVertex, secondVertex, thirdVertex;
    private BigNumber area, perimeter;

    public Triangle(BigNumber x1, BigNumber y1, BigNumber x2, BigNumber y2, BigNumber x3, BigNumber y3){
        firstVertex = new Dot(x1, y1);
        secondVertex = new Dot(x2, y2);
        thirdVertex = new Dot(x3, y3);
        areaCalculation();
        perimeterCalculation();
    }

    public void areaCalculation() {
        BigNumber p = secondVertex.getX().subtract(firstVertex.getX());
        BigNumber s = thirdVertex.getY().subtract(firstVertex.getY());
        BigNumber q = thirdVertex.getX().subtract(firstVertex.getX());
        BigNumber r = secondVertex.getY().subtract(firstVertex.getY());

        BigNumber determinant = p.multiply(s).subtract(r.multiply(q));
        try {
            this.area = determinant.divide(new BigNumber("2"));
        } catch (Exception e) {
            this.area = new BigNumber("0");
        }

}

    public void perimeterCalculation() {
        BigNumber firstLength = new Vector(firstVertex, secondVertex).getLength();
        BigNumber secondLength = new Vector(secondVertex, thirdVertex).getLength();
        BigNumber thirdLength = new Vector(thirdVertex, firstVertex).getLength();
        this.perimeter = (firstLength.add(secondLength)).add(thirdLength);
    }

    public BigNumber getArea() {
        return area;
    }

    public BigNumber getPerimeter() {
        return perimeter;
    }

    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public String accept(IShapeVisitor visitor) {
        return visitor.visit(this);
    }
}

