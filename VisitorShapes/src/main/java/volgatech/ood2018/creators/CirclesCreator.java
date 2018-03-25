package volgatech.ood2018.creators;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.IShape;

public class CirclesCreator implements IShapesCreator {
    private static CirclesCreator instance = null;

    private CirclesCreator() {}


    public static CirclesCreator getInstance() {
        CirclesCreator creator = instance;
        if (creator == null) {
            creator = new CirclesCreator();
        }
        return creator;
    }

    @Override
    public IShape create(String params) {
        String[] circleParams = params.split(" ");
        return new Circle(new BigNumber(circleParams[0]), new BigNumber(circleParams[1]),
                new BigNumber(circleParams[2]));
    }
}
