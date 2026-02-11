package main;

/**
 * Implementación del algoritmo de ordenamiento Merge Sort.
 *
 * Algoritmo basado en divide y vencerás que divide el arreglo
 * en subarreglos y luego los combina de forma ordenada.
 *
 * Complejidad temporal: O(n log n)
 * Complejidad espacial: O(n)
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 */
public class MergeSort implements SortAlgorithms {

    /**
     * Ordena el arreglo recibido utilizando el algoritmo Merge Sort.
     *
     * @param <T> Tipo genérico que extiende Comparable.
     * @param array Arreglo a ordenar.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        mergeSortIterative(array);
    }

    /**
     * Implementación iterativa del algoritmo Merge Sort.
     * Divide el arreglo en subarreglos de tamaño creciente y los combina
     * progresivamente hasta que el arreglo completo queda ordenado.
     *
     * @param <T> Tipo genérico comparable.
     * @param array Arreglo a ordenar.
     */
    private <T extends Comparable<T>> void mergeSortIterative(T[] array) {
        int n = array.length;

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {

            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {

                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(array, leftStart, mid, rightEnd);
            }
        }
    }

    /**
     * Combina dos subarreglos ordenados en un único subarreglo ordenado.
     *
     * @param <T> Tipo genérico comparable.
     * @param array Arreglo principal.
     * @param left Índice inicial del primer subarreglo.
     * @param middle Índice final del primer subarreglo.
     * @param right Índice final del segundo subarreglo.
     */
    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        T[] leftArray = (T[]) new Comparable[n1];
        T[] rightArray = (T[]) new Comparable[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * Retorna el nombre del algoritmo.
     *
     * @return Nombre del algoritmo.
     */
    @Override
    public String getName() {
        return "Merge Sort";
    }
}
