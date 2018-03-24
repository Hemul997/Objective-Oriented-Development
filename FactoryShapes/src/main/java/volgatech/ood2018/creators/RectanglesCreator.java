package volgatech.ood2018.creators;

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
        return new Rectangle(Integer.parseInt(rectangleParams[0]), Integer.parseInt(rectangleParams[1]),
                Integer.parseInt(rectangleParams[2]), Integer.parseInt(rectangleParams[3]));
    }
}
