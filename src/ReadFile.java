import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public static Integer[] readNumbersFromFile(String filePath) throws FileNotFoundException {
        List<Integer> numbers = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        }
        return numbers.toArray(new Integer[0]);
    }
}
