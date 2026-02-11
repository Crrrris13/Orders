package main;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        try {
            System.out.println("=== HOJA DE TRABAJO 03 - ORDENAMIENTOS ===\n");
            
            System.out.println("Generando números aleatorios...");
            generarNumeros("numeros.txt", 3000);
            
            System.out.println("\nPaso 2: Midiendo tiempos de ejecución...\n");
            medirTiempos();
            
            System.out.println("\nPrograma completo");
            System.out.println("Revisa el archivo 'resultados.csv' para hacer la gráfica en Excel");
            
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
        System.out.println("Se han generado " + cantidad + " números en '" + archivo + "'");
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
            }
            
            System.out.println();
        }
        
        csv.close();
        System.out.println("Resultados en 'resultados.csv'");
    }
    static long medirTiempo(SortAlgorithms algoritmo, Integer[] numeros) {
        long inicio = System.nanoTime();
        
        algoritmo.sort(numeros);
        
        long fin = System.nanoTime();
        
        long tiempoNanos = fin - inicio;
        long tiempoMilis = tiempoNanos / 1_000_000; // Convertir a milisegundos
        
        return tiempoMilis;
    }
    }