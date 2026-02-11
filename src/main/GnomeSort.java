package main;

/**
 * Implementación del algoritmo de ordenamiento Gnome Sort.
 *
 * Algoritmo basado en comparaciones que intercambia
 * elementos adyacentes hasta ordenar el arreglo.
 *
 * Complejidad temporal: O(n^2)
 * Complejidad espacial: O(1)
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 */

public class GnomeSort implements SortAlgorithms {

    /**
     * Ordena un arreglo utilizando el algoritmo Gnome Sort.
     *
     * @param <T> Tipo genérico que extiende Comparable.
     * @param array Arreglo a ordenar.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int index = 0;

        while (index < array.length) {
            if (index == 0) {
                index++;
            } else if (array[index].compareTo(array[index - 1]) >= 0) {
                index++;
            } else {
                swap(array, index, index - 1);
                index--;
            }
        }
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
        return "Gnome Sort";
    }
}
