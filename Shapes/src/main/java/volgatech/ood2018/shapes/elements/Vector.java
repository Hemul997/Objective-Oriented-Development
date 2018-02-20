package volgatech.ood2018.shapes.elements;

import volgatech.ood2018.shapes.elements.Dot;

public class Vector {
    private Dot begin, end;
    private double length;

    public Vector(Dot dot1, Dot dot2) {
        this.begin = dot1;
        this.end = dot2;
        calculateLength();
    }

    public double getLength() {
        return length;
    }

    public void calculateLength() {
        this.length = Math.sqrt(Math.pow(this.end.getX() - this.begin.getX(), 2) +
                Math.pow(this.end.getY() - this.begin.getY(), 2));


    }
    public String toString() {
        return (Integer.toString(this.begin.getX()) + ' ' + Integer.toString(this.begin.getY()) + ' ' +
                Integer.toString(this.end.getX()) + ' ' + Integer.toString(this.end.getY()) );
    }
}
