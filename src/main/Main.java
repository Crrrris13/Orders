package main;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Clase principal para el análisis y comparación de algoritmos de ordenamiento.
 *
 * Esta clase genera números aleatorios, ejecuta diferentes algoritmos
 * de ordenamiento, mide sus tiempos de ejecución y exporta los resultados
 * a un archivo CSV para su posterior análisis.
 *
 * El programa prueba cada algoritmo en dos escenarios:
 * - Arreglos desordenados (caso promedio)
 * - Arreglos previamente ordenados (mejor caso)
 *
 * Los tiempos de ejecución se miden utilizando System.nanoTime()
 * y se almacenan en milisegundos.
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 * @version 1.0
 * @since 2026-02-11
 */
public class Main {

    /**
     * Método principal que coordina la ejecución del programa.
     * Genera números aleatorios, mide los tiempos de los algoritmos
     * y guarda los resultados en un archivo CSV.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        try {
            generateNumbers("numbers.txt", 3000);
            measureTimes();
            System.out.println("Completed. Check 'results.csv'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un archivo de texto con números enteros aleatorios.
     *
     * @param filename nombre del archivo donde se guardarán los números
     * @param count cantidad de números aleatorios a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    static void generateNumbers(String filename, int count) throws IOException {
        Random rand = new Random();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for (int i = 0; i < count; i++) {
            int number = rand.nextInt(10000);
            writer.write(number + "\n");
        }

        writer.close();
    }

    /**
     * Lee números enteros desde un archivo de texto.
     * Cada línea del archivo debe contener exactamente un número entero.
     *
     * @param filename nombre del archivo a leer
     * @param count cantidad de números a leer del archivo
     * @return arreglo de Integer con los números leídos
     * @throws IOException si ocurre un error al leer el archivo
     * @throws NumberFormatException si una línea no contiene un número válido
     */
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

    /**
     * Mide los tiempos de ejecución de todos los algoritmos de ordenamiento
     * utilizando diferentes tamaños de entrada y escenarios.
     *
     * Los resultados se almacenan en un archivo CSV con el formato:
     * Algorithm,Size,Time_ms,Scenario
     *
     * @throws IOException si ocurre un error al leer o escribir archivos
     * @see #measureTime(SortAlgorithms, Integer[])
     * @see #readNumbers(String, int)
     */
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

    /**
     * Mide el tiempo de ejecución de un algoritmo de ordenamiento específico.
     * El tiempo se calcula en nanosegundos utilizando System.nanoTime()
     * y posteriormente se convierte a milisegundos.
     *
     * El arreglo recibido como parámetro será modificado
     * ya que el algoritmo lo ordenará.
     *
     * @param algorithm algoritmo de ordenamiento a medir
     * @param numbers arreglo de números a ordenar (será modificado)
     * @return tiempo de ejecución en milisegundos
     * @see SortAlgorithms#sort(Comparable[])
     */
    static long measureTime(SortAlgorithms algorithm, Integer[] numbers) {
        long start = System.nanoTime();
        algorithm.sort(numbers);
        long end = System.nanoTime();

        long timeNanos = end - start;
        long timeMillis = timeNanos / 1_000_000;

        return timeMillis;
    }
}
