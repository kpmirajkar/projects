package co.in.kpm.sorting;

public class Quicksort {

  private static void quickSort(int[] A, int low, int high) {
    if(low < high) {
      int pivotIndex = partition(A, low, high);
      quickSort(A, low, pivotIndex - 1);
      quickSort(A, pivotIndex + 1, high);
    }
  }


  private static int partition(int[] A, int low, int high) {
    int pivot = A[high];
    int i = low - 1;
    int tmp = 0;
    for(int j = low; j < high; j++) {
      if(A[j] <= pivot) {
        i = i + 1;
        tmp = A[j];
        A[j] = A[i];
        A[i] = tmp;
      }
    }
    tmp = A[i + 1];
    A[i + 1] = A[high];
    A[high] = tmp;
    return i + 1;
  }


  public static void main(String[] args) {
    int[] A = new int[] { 3, 2, 5, 6, 7, 4, 11, 1, 98 };
    quickSort(A, 0, A.length - 1);
    for(int i = 0; i < A.length; i++) {
      System.out.print(A[i] + " ");
    }
  }
}
