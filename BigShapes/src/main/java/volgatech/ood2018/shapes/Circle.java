package volgatech.ood2018.shapes;

import volgatech.ood2018.shapes.elements.Dot;

public class Circle implements IShape {
    private Dot center;
    private int radius;
    private double area, perimeter;

    public Circle(int x1, int y1, int radius) {
        this.center = new Dot(x1, y1);
        this.radius = radius;
        areaCalculation();
        perimeterCalculation();
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    @Override
    public String getName() {
        return "Circle";

    }

    public void areaCalculation() {
        this.area = Math.PI * Math.pow(this.radius, 2);
    }

    public void perimeterCalculation() {
        this.perimeter = 2 * Math.PI * this.radius;
    }


}
