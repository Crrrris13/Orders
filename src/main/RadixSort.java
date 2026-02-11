package main;

import java.util.Arrays;

/**
 * Implementación del algoritmo de ordenamiento Radix Sort.
 *
 * Algoritmo no comparativo que ordena números procesando
 * cada dígito individualmente.
 *
 * Complejidad temporal: O(d * (n + k))
 * Complejidad espacial: O(n + k)
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 */
public class RadixSort implements SortAlgorithms {

    /**
     * Ordena el arreglo utilizando el algoritmo Radix Sort.
     *
     * @param <T> Tipo genérico que extiende Comparable.
     * @param array Arreglo a ordenar.
     * @throws IllegalArgumentException si el arreglo no contiene Integer.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        if (!(array[0] instanceof Integer)) {
            throw new IllegalArgumentException(
                "Radix Sort requiere un arreglo de Integer"
            );
        }

        Integer[] intArray = (Integer[]) array;
        radixSort(intArray);

        System.arraycopy(intArray, 0, array, 0, array.length);
    }

    /**
     * Método principal del algoritmo Radix Sort.
     * Ordena el arreglo procesando cada dígito desde el menos significativo
     * hasta el más significativo.
     *
     * @param array Arreglo de enteros a ordenar.
     */
    private void radixSort(Integer[] array) {
        int max = getMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    /**
     * Obtiene el valor absoluto máximo del arreglo.
     *
     * @param array Arreglo de enteros.
     * @return Valor máximo absoluto encontrado.
     */
    private int getMax(Integer[] array) {
        int max = Math.abs(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (Math.abs(array[i]) > max) {
                max = Math.abs(array[i]);
            }
        }
        return max;
    }

    /**
     * Counting Sort modificado para ordenar según un dígito específico.
     *
     * @param array Arreglo de enteros.
     * @param exp Exponente correspondiente a la posición decimal (1, 10, 100, etc.).
     */
    private void countingSort(Integer[] array, int exp) {
        int n = array.length;
        Integer[] output = new Integer[n];
        int[] count = new int[19]; // Rango de -9 a 9

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            int digit = getDigit(array[i], exp);
            count[digit + 9]++;
        }

        for (int i = 1; i < 19; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = getDigit(array[i], exp);
            output[count[digit + 9] - 1] = array[i];
            count[digit + 9]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    /**
     * Obtiene el dígito correspondiente a una posición decimal específica.
     *
     * @param number Número del cual se extrae el dígito.
     * @param exp Exponente decimal.
     * @return Dígito en la posición indicada.
     */
    private int getDigit(int number, int exp) {
        return (number / exp) % 10;
    }

    /**
     * Retorna el nombre del algoritmo.
     *
     * @return Nombre del algoritmo.
     */
    @Override
    public String getName() {
        return "Radix Sort";
    }
}
