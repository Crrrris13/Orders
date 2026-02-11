package main;

/**
 * Implementación del algoritmo de ordenamiento Quick Sort.
 *
 * Algoritmo basado en divide y vencerás que utiliza un pivote
 * para dividir el arreglo en subarreglos.
 *
 * Complejidad temporal: O(n log n) promedio, O(n^2) peor caso
 * Complejidad espacial: O(log n)
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 */

public class QuickSort implements SortAlgorithms {

    /**
     * Ordena el arreglo utilizando el algoritmo Quick Sort.
     *
     * @param <T> Tipo genérico que extiende Comparable.
     * @param array Arreglo a ordenar.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Método recursivo que divide el arreglo en subarreglos
     * alrededor de un pivote.
     *
     * @param <T> Tipo genérico comparable.
     * @param array Arreglo a ordenar.
     * @param low Índice inicial del subarreglo.
     * @param high Índice final del subarreglo.
     */
    private <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Realiza la partición del arreglo utilizando el último elemento
     * como pivote (esquema de Lomuto).
     *
     * @param <T> Tipo genérico comparable.
     * @param array Arreglo principal.
     * @param low Índice inicial.
     * @param high Índice final.
     * @return Índice final del pivote después de la partición.
     */
    private <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Intercambia dos elementos dentro del arreglo.
     *
     * @param <T> Tipo del arreglo.
     * @param array Arreglo donde se realizará el intercambio.
     * @param i Índice del primer elemento.
     * @param j Índice del segundo elemento.
     */
    private <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Retorna el nombre del algoritmo.
     *
     * @return Nombre del algoritmo.
     */
    @Override
    public String getName() {
        return "Quick Sort";
    }
}
