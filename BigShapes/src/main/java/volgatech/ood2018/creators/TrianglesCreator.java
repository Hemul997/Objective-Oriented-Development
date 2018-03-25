package volgatech.ood2018.creators;

import volgatech.ood2018.bignumber.BigNumber;
import volgatech.ood2018.shapes.IShape;
import volgatech.ood2018.shapes.Triangle;

public class TrianglesCreator implements IShapesCreator {
    private static TrianglesCreator instance = null;

    private TrianglesCreator() {}

    public static TrianglesCreator getInstance() {
        TrianglesCreator creator = instance;
        if (creator == null) {
            creator = new TrianglesCreator();
        }
        return creator;
    }

    @Override
    public IShape create(String line) {
        String[] shapeParams = line.split(" ");;
        return new Triangle(new BigNumber(shapeParams[0]), new BigNumber(shapeParams[1]),
                new BigNumber(shapeParams[2]), new BigNumber(shapeParams[3]),
                new BigNumber(shapeParams[4]), new BigNumber(shapeParams[5]));
    }
}
