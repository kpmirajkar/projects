package co.in.apr.sorting;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    int[] array = new int[] { 99, 76, 7, 2, 1, 6, 8, 5, 3, 4, 15, 0, 20, 80, 45 };
    quickSort(array, 0, array.length - 1);
    System.out.println("=======================Sorted Array======================= ");
    for(int i : array) {
      System.out.println(i);
    }
  }


  private static void quickSort(int[] array, int start, int end) {
    if(start < end) {
      int pIndex = partition(array, start, end);
      quickSort(array, start, pIndex - 1);
      quickSort(array, pIndex + 1, end);
    }

  }


  private static int partition(int[] array, int start, int end) {
    int pIndex = start;
    int pivot = array[end];

    for(int i = start; i < end; i++) {
      if(array[i] <= pivot) {
        swap(array, i, pIndex);
        pIndex++;
      }
    }
    swap(array, pIndex, end);
    return pIndex;
  }


  private static void swap(int[] array, int pos1, int pos2) {
    int temp = array[pos1];
    array[pos1] = array[pos2];
    array[pos2] = temp;
    System.out.println("Swapped value " + array[pos1] + " with " + array[pos2]);
    System.out.println(Arrays.toString(array));
  }
}
