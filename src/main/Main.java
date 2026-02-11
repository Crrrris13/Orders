package main;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        try {
            generateNumbers("numbers.txt", 3000);
            measureTimes();
            System.out.println("Completed. Check 'results.csv'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void generateNumbers(String filename, int count) throws IOException {
        Random rand = new Random();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        
        for (int i = 0; i < count; i++) {
            int number = rand.nextInt(10000);
            writer.write(number + "\n");
        }
        
        writer.close();
    }
    
    static Integer[] readNumbers(String filename, int count) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Integer[] numbers = new Integer[count];
        
        for (int i = 0; i < count; i++) {
            String line = reader.readLine();
            numbers[i] = Integer.parseInt(line);
        }
        
        reader.close();
        return numbers;
    }
    
    static void measureTimes() throws IOException {
        SortAlgorithms[] algorithms = {
            new GnomeSort(),
            new MergeSort(),
            new QuickSort(),
            new RadixSort(),
            new CountingSort()
        };
        
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 3000};
        
        BufferedWriter csv = new BufferedWriter(new FileWriter("results.csv"));
        csv.write("Algorithm,Size,Time_ms,Scenario\n");
        
        for (int size : sizes) {
            Integer[] originalNumbers = readNumbers("numbers.txt", size);
            
            for (SortAlgorithms algorithm : algorithms) {
                
                Integer[] unsortedNumbers = Arrays.copyOf(originalNumbers, size);
                long time1 = measureTime(algorithm, unsortedNumbers);
                csv.write(algorithm.getName() + "," + size + "," + time1 + ",Unsorted\n");
                
                Integer[] sortedNumbers = Arrays.copyOf(originalNumbers, size);
                Arrays.sort(sortedNumbers);
                long time2 = measureTime(algorithm, sortedNumbers);
                csv.write(algorithm.getName() + "," + size + "," + time2 + ",Sorted\n");
            }
        }
        
        csv.close();
    }
    
    static long measureTime(SortAlgorithms algorithm, Integer[] numbers) {
        long start = System.nanoTime();
        algorithm.sort(numbers);
        long end = System.nanoTime();
        
        long timeNanos = end - start;
        long timeMillis = timeNanos / 1_000_000;
        
        return timeMillis;
    }
}
