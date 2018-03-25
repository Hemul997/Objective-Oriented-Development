package volgatech.ood2018.shapes;

import volgatech.ood2018.shapes.elements.Vector;
import volgatech.ood2018.shapes.elements.Dot;

public class Triangle implements IShape {
    private Dot firstVertex, secondVertex, thirdVertex;
    private double area, perimeter;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3){
        firstVertex = new Dot(x1, y1);
        secondVertex = new Dot(x2, y2);
        thirdVertex = new Dot(x3, y3);
        areaCalculation();
        perimeterCalculation();
    }

    public void areaCalculation() {
        int p = secondVertex.getX() - firstVertex.getX();
        int s = thirdVertex.getY() - firstVertex.getY();
        int q = thirdVertex.getX() - firstVertex.getX();
        int r = secondVertex.getY() - firstVertex.getY();

        double determinant = Math.abs((p * s) - (r * q));

        this.area = determinant / 2;
    }

    public void perimeterCalculation() {
        double firstLength = new Vector(firstVertex, secondVertex).getLength();
        double secondLength = new Vector(secondVertex, thirdVertex).getLength();
        double thirdLength = new Vector(thirdVertex, firstVertex).getLength();
        this.perimeter = firstLength + secondLength + thirdLength;

    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    @Override
    public String getName() {
        return "Triangle";
    }
}

