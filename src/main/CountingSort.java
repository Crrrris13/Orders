package main;

public class CountingSort implements SortAlgorithms{
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        if (!(array[0] instanceof Integer)) {
            throw new IllegalArgumentException("Counting Sort requiere un arreglo de Integer");
        }
        
        Integer[] intArray = (Integer[]) array;
        countingSort(intArray);
        
        System.arraycopy(intArray, 0, array, 0, array.length);
    }
    

    private void countingSort(Integer[] array) {
        int n = array.length;
        
        int min = array[0];
        int max = array[0];
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
        
        for (int i = 0; i < n; i++) {
            count[array[i] - min]++;
        }
        
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int value = array[i];
            int index = value - min;
            output[count[index] - 1] = value;
            count[index]--;
        }
        
        System.arraycopy(output, 0, array, 0, n);
    }
    
    @Override
    public String getName() {
        return "Counting Sort";
    }
}

