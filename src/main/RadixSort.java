package main;
import java.util.Arrays;
public class RadixSort implements SortAlgorithms {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        if (!(array[0] instanceof Integer)) {
            throw new IllegalArgumentException("Radix Sort requiere un arreglo de Integer");
        }
        
        Integer[] intArray = (Integer[]) array;
        radixSort(intArray);
        
        System.arraycopy(intArray, 0, array, 0, array.length);
    }
    
    private void radixSort(Integer[] array) {
        int max = getMax(array);
        
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }
    
    private int getMax(Integer[] array) {
        int max = Math.abs(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (Math.abs(array[i]) > max) {
                max = Math.abs(array[i]);
            }
        }
        return max;
    }
    
    private void countingSort(Integer[] array, int exp) {
        int n = array.length;
        Integer[] output = new Integer[n];
        int[] count = new int[19]; // -9 a 9 mapeados a 0-18

        Arrays.fill(count, 0);
        

        for (int i = 0; i < n; i++) {
            int digit = getDigit(array[i], exp);
            count[digit + 9]++; // +9 para manejar negativos
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
    

    private int getDigit(int number, int exp) {
        return (number / exp) % 10;
    }
    
    @Override
    public String getName() {
        return "Radix Sort";
    }
}