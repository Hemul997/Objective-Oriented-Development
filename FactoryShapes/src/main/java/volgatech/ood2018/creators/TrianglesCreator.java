package volgatech.ood2018.creators;

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
        return new Triangle(Integer.parseInt(shapeParams[0]), Integer.parseInt(shapeParams[1]),
                Integer.parseInt(shapeParams[2]), Integer.parseInt(shapeParams[3]),
                Integer.parseInt(shapeParams[4]), Integer.parseInt(shapeParams[5]));
    }
}
