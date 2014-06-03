package co.in.apr.sorting;

import java.util.Arrays;

public class SelectionSort {

  public static void main(String[] args) {
    int[] array = new int[] { 7, 2, 1, 6, 8, 5, 3, 4, 10, 34, 15, 26, };
    selectionSort(array, array.length);
    System.out.println(Arrays.toString(array));

  }


  private static void selectionSort(int[] array, int length) {
    for(int i = 0; i < length - 1; i++) {
      int min = i;
      for(int j = i + 1; j < length; j++) {
        if(array[j] < array[min]) {
          min = j;
        }
      }
      int temp = array[i];
      array[i] = array[min];
      array[min] = temp;
    }
  }
}
