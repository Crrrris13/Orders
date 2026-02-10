package main;

public class GnomeSort implements SortAlgorithms{
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
    

    private <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    @Override
    public String getName() {
        return "Gnome Sort";
    }
}
