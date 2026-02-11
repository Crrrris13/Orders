package main;

/**
 * Interfaz que define el comportamiento común para todos los
 * algoritmos de ordenamiento implementados en el proyecto.
 *
 * @author Cristopher Chavez
 * @author Fabricio Estrada
 */
public interface SortAlgorithms {

    /**
     * Ordena el arreglo recibido como parámetro.
     *
     * @param <T> Tipo genérico que debe implementar Comparable.
     * @param array Arreglo a ordenar.
     */
    <T extends Comparable<T>> void sort(T[] array);

    /**
     * Retorna el nombre del algoritmo de ordenamiento.
     *
     * @return Nombre del algoritmo.
     */
    String getName();
}
