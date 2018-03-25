package volgatech.ood2018.shapes;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.elements.Vector;
import volgatech.ood2018.shapes.elements.Dot;

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
        /*int p = secondVertex.getX() - firstVertex.getX();
        int s = thirdVertex.getY() - firstVertex.getY();
        int q = thirdVertex.getX() - firstVertex.getX();
        int r = secondVertex.getY() - firstVertex.getY();

        double determinant = Math.abs((p * s) - (r * q));
        this.area = determinant / 2;*/

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
        //this.perimeter = firstLength + secondLength + thirdLength;
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
}

