package main;

/**
 * Implementación del algoritmo de ordenamiento Counting Sort.
 *
 * Counting Sort es un algoritmo no comparativo que ordena
 * enteros contando la cantidad de apariciones de cada valor.
 *
 * Complejidad temporal: O(n + k)
 * Complejidad espacial: O(n + k)
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 */

public class CountingSort implements SortAlgorithms {

    /**
     * Ordena el arreglo recibido utilizando el algoritmo Counting Sort.
     *
     * @param <T> Tipo genérico que extiende Comparable.
     * @param array Arreglo a ordenar.
     * @throws IllegalArgumentException si el arreglo no contiene elementos Integer.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        if (!(array[0] instanceof Integer)) {
            throw new IllegalArgumentException(
                "Counting Sort requiere un arreglo de Integer"
            );
        }

        Integer[] intArray = (Integer[]) array;
        countingSort(intArray);

        System.arraycopy(intArray, 0, array, 0, array.length);
    }

    /**
     * Método interno que contiene la lógica del Counting Sort.
     *
     * @param array Arreglo de enteros a ordenar.
     */
    private void countingSort(Integer[] array) {
        int n = array.length;

        int min = array[0];
        int max = array[0];

        // Encontrar valores mínimo y máximo
        for (int i = 1; i < n; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        int range = max - min + 1;

        int[] count = new int[range];
        Integer[] output = new Integer[n];

        // Contar ocurrencias
        for (int i = 0; i < n; i++) {
            count[array[i] - min]++;
        }

        // Acumular conteos
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Construir arreglo ordenado
        for (int i = n - 1; i >= 0; i--) {
            int value = array[i];
            int index = value - min;
            output[count[index] - 1] = value;
            count[index]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    /**
     * Retorna el nombre del algoritmo.
     *
     * @return Nombre del algoritmo.
     */
    @Override
    public String getName() {
        return "Counting Sort";
    }
}
