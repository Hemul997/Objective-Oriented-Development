package volgatech.ood2018.shapes.elements;

import volgatech.ood2018.bignumber.BigNumber;

public class Dot {
    private BigNumber x, y;

    public Dot(BigNumber x, BigNumber y) {
        this.x = x;
        this.y = y;
    }

    public BigNumber getX() {
        return x;
    }

    public BigNumber getY() {
        return y;
    }
}
