package volgatech.ood2018.shapes;

import volgatech.ood2018.shapes.elements.Dot;

public class Rectangle implements IShape {

    private Dot firstVertex, secondVertex;
    private int height , length;
    private double area, perimeter;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.firstVertex = new Dot(x1, y1);
        this.secondVertex = new Dot(x2, y2);
        this.height = secondVertex.getY() - firstVertex.getY();
        this.length = secondVertex.getX() - firstVertex.getX();
        areaCalculation();
        perimeterCalculation();
    }

    public void areaCalculation() {
        this.area = Math.abs(this.height * this.length);
    }

    public void perimeterCalculation() {
        this.perimeter = Math.abs(2 * (this.length + this.height));
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }
}
