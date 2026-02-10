import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CreateFile {
    public static void generateRandomNumbers(String filePath, int count) throws IOException {
        Random rand = new Random();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                writer.println(rand.nextInt(3001));
            }
        }
    }
}