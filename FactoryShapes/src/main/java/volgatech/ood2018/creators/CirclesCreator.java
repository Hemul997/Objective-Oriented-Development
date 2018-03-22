package volgatech.ood2018.creators;

import volgatech.ood2018.shapes.Circle;
import volgatech.ood2018.shapes.IShape;

public class CirclesCreator implements IShapesCreator {
    private static CirclesCreator instance = null;

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
        return new Circle(Integer.parseInt(circleParams[0]), Integer.parseInt(circleParams[1]),
                Integer.parseInt(circleParams[2]));
    }
}
