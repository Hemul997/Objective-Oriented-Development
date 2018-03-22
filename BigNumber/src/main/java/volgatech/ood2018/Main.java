package volgatech.ood2018;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> arguments;
        String inpFile = args[0];
        String outFile = args[1];

        Path path = Paths.get(inpFile);
        try {
            arguments = Files.readAllLines(path);
            FileWriter writer = new FileWriter(outFile);
            BigNumberController bigNumberController = new BigNumberController();
            for (String line : arguments) {
                bigNumberController.setParams(line);
                writer.write(line + " = " + bigNumberController.processResult() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
