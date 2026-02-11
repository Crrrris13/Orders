package main;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        try {
            System.out.println("=== HOJA DE TRABAJO 03 - ORDENAMIENTOS ===");
            System.out.println("=== MODO PROFILING CON VISUALVM ===\n");
            
            Thread.sleep(5000); 
            

            generarNumeros("numeros.txt", 3000);
            
            medirTiempos();

            System.out.println("\nEsperando 3 segundos antes de cerrar...");
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    static void generarNumeros(String archivo, int cantidad) throws IOException {
        Random rand = new Random();
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        
        for (int i = 0; i < cantidad; i++) {
            int numero = rand.nextInt(10000);
            writer.write(numero + "\n");
        }
        
        writer.close();
        System.out.println(" Generados " + cantidad + " números en '" + archivo + "'");
    }
    

    static Integer[] leerNumeros(String archivo, int cantidad) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        Integer[] numeros = new Integer[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            String linea = reader.readLine();
            numeros[i] = Integer.parseInt(linea);
        }
        
        reader.close();
        return numeros;
    }
    

    static void medirTiempos() throws IOException {
        // Los 5 algoritmos a probar
        SortAlgorithms[] algoritmos = {
            new GnomeSort(),
            new MergeSort(),
            new QuickSort(),
            new RadixSort(),
            new CountingSort()
        };
        
        int[] tamaños = {10, 50, 100, 500, 1000, 2000, 3000};
        
        BufferedWriter csv = new BufferedWriter(new FileWriter("resultados.csv"));
        csv.write("Algoritmo,Tamaño,Tiempo_ms,Escenario\n");
        
        int repeticiones = 5; 
        
        for (int rep = 1; rep <= repeticiones; rep++) {
            System.out.println(">>> Repetición " + rep + " de " + repeticiones + " <<<\n");
            
            for (int tamaño : tamaños) {
                System.out.println("Probando con " + tamaño + " números:");
                

                Integer[] numerosOriginales = leerNumeros("numeros.txt", tamaño);
                
                for (SortAlgorithms algoritmo : algoritmos) {
                    
                    Integer[] numerosDesordenados = Arrays.copyOf(numerosOriginales, tamaño);
                    long tiempo1 = medirTiempo(algoritmo, numerosDesordenados);
                    csv.write(algoritmo.getName() + "," + tamaño + "," + tiempo1 + ",Desordenado\n");
                    System.out.println("  " + algoritmo.getName() + " (desordenado): " + tiempo1 + " ms");
                    
                    Integer[] numerosOrdenados = Arrays.copyOf(numerosOriginales, tamaño);
                    Arrays.sort(numerosOrdenados);
                    long tiempo2 = medirTiempo(algoritmo, numerosOrdenados);
                    csv.write(algoritmo.getName() + "," + tamaño + "," + tiempo2 + ",Ordenado\n");
                    System.out.println("  " + algoritmo.getName() + " (ordenado): " + tiempo2 + " ms");
                    
                    try {
                        Thread.sleep(100); 
                    } catch (InterruptedException e) {
                        // Ignorar
                    }
                }
                
                System.out.println();
            }
        }
        
        csv.close();
    }
    

    static long medirTiempo(SortAlgorithms algoritmo, Integer[] numeros) {
        long inicio = System.nanoTime();
        algoritmo.sort(numeros);
        long fin = System.nanoTime();
        
        long tiempoNanos = fin - inicio;
        long tiempoMilis = tiempoNanos / 1_000_000;
        
        return tiempoMilis;
    }
}
