package volgatech.ood2018;

import volgatech.ood2018.controllers.ShapeController;
import volgatech.ood2018.shapes.IShape;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> arguments;
        ShapeController shapeController = new ShapeController();
        if (args.length != 2) {
            System.out.println("Wrong size of arguments");
        }
        String inpFile = args[0];
        String outFile = args[1];

        Path path = Paths.get(inpFile);

        try {
            arguments = Files.readAllLines(path);
            FileWriter writer = new FileWriter(outFile);
            for (String line : arguments) {
                IShape shape = shapeController.makeShape(line);
                if (shape != null) {
                    shapeController.printShapeData(shape, writer);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
