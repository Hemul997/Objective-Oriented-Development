package volgatech.ood2018.shapes.elements;

import volgatech.ood2018.bignumber.BigNumber;

public class Vector {
    private Dot begin, end;
    private BigNumber length;

    public Vector(Dot dot1, Dot dot2) {
        this.begin = dot1;
        this.end = dot2;
        calculateLength();
    }

    public BigNumber getLength() {
        return length;
    }

    private void calculateLength() {
        BigNumber xCoordinatesDistance = this.end.getX().subtract(this.begin.getX()).pow(2);
        BigNumber yCoordinatesDistance = this.end.getY().subtract(this.begin.getY()).pow(2);
        BigNumber sumOfDistances = xCoordinatesDistance.add(yCoordinatesDistance);
        this.length = sumOfDistances.sqrt();
//        /*this.length = Math.sqrt(Math.pow(this.end.getX() - this.begin.getX(), 2) +
//                Math.pow(this.end.getY() - this.begin.getY(), 2));*/


    }
    public String toString() {
        return (this.begin.getX().toString() + ' ' + this.begin.getY().toString() + ' ' +
                this.end.getX().toString() + ' ' + this.end.getY().toString());
    }
}
