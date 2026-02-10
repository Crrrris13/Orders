package main;
public class QuickSort implements SortAlgorithms {
@Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }
    
    private <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low < high) {
            // Encuentra la posición de partición
            int pivotIndex = partition(array, low, high);
            
            // Ordena elementos antes y después del pivote
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    private <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        // Seleccionamos el último elemento como pivote
        T pivot = array[high];
        
        // Índice del elemento más pequeño
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    private <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    @Override
    public String getName() {
        return "Quick Sort";
    }
}

