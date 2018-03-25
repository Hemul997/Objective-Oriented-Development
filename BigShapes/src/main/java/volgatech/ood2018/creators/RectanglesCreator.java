package volgatech.ood2018.creators;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.IShape;
import volgatech.ood2018.shapes.Rectangle;

public class RectanglesCreator implements IShapesCreator{
    private static RectanglesCreator instance = null;

    private RectanglesCreator() {}

    public static RectanglesCreator getInstance() {
        RectanglesCreator creator = instance;
        if (creator == null) {
            creator = new RectanglesCreator();
        }
        return creator;
    }

    @Override
    public IShape create(String params) {
        String[] rectangleParams = params.split(" ");
        return new Rectangle(new BigNumber(rectangleParams[0]), new BigNumber(rectangleParams[1]),
                new BigNumber(rectangleParams[2]), new BigNumber(rectangleParams[3]));
    }
}
