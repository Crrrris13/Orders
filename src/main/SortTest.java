package main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SortTest {
    @Test
    public void TestGnomeSortOrderedArray() {
        GnomeSort gnomeSort = new GnomeSort();
        Integer[] array = {1, 2, 3, 4, 5};
        gnomeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestGnomeSortUnorderedArray() {
        GnomeSort gnomeSort = new GnomeSort();
        Integer[] array = {5, 3, 1, 4, 2};
        gnomeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestMergeSortOrderedArray() {
        MergeSort mergeSort = new MergeSort();
        Integer[] array = {1, 2, 3, 4, 5};
        mergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestMergeSortUnorderedArray() {
        MergeSort mergeSort = new MergeSort();
        Integer[] array = {5, 3, 1, 4, 2};
        mergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestQuickSortOrderedArray() {
        QuickSort quickSort = new QuickSort();
        Integer[] array = {1, 2, 3, 4, 5};
        quickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestQuickSortUnorderedArray() {
        QuickSort quickSort = new QuickSort();
        Integer[] array = {5, 3, 1, 4, 2};
        quickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestRadixSortOrderedArray() {
        RadixSort radixSort = new RadixSort();
        Integer[] array = {1, 2, 3, 4, 5};
        radixSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestRadixSortUnorderedArray() { 
        RadixSort radixSort = new RadixSort();
        Integer[] array = {5, 3, 1, 4, 2};
        radixSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestCountingSortOrderedArray() {
        CountingSort countingSort = new CountingSort();
        Integer[] array = {1, 2, 3, 4, 5};
        countingSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void TestCountingSortUnorderedArray() {
        CountingSort countingSort = new CountingSort();
        Integer[] array = {5, 3, 1, 4, 2};
        countingSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }
}
