package co.in.kpm.sorting;

import java.util.Arrays;

public class InsertionSort {

  public static void main(String[] args) {
    int[] array = new int[] { 7, 2, 1, 6, 8, 5, 3, 4, 10, 34, 15, 26, };
    insertionSort(array, array.length);
    System.out.println(Arrays.toString(array));

  }


  private static void insertionSort(int[] array, int length) {
    for(int i = 1; i < length - 1; i++) {
      int value = array[i];
      int hole = i;
      while(hole > 0 && array[hole - 1] > value) {
        array[hole] = array[hole - 1];
        hole--;
      }
      array[hole] = value;
    }
  }
}
