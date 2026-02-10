package main;

public interface  SortAlgorithms {
    <T extends Comparable<T>> void sort(T[] array);
    String getName();
}
