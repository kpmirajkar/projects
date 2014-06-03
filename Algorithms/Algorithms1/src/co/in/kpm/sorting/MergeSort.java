package co.in.kpm.sorting;

public class MergeSort {

  private static int[] mergeSort(int[] A, int low, int high) {
    if(low < high) {
      int mid = (high + low) / 2;
      mergeSort(A, low, mid);
      mergeSort(A, mid + 1, high);
      merge(A, low, mid, high);
    }
    return A;
  }


  private static void merge(int[] A, int low, int mid, int high) {
    int leftN = mid - low + 1;
    int rightN = high - mid;
    int[] L = new int[leftN + 1];
    int[] R = new int[rightN + 1];
    for(int i = 0; i < leftN; i++)
      L[i] = A[low + i - 1];
    for(int j = 0; j < rightN; j++)
      R[j] = A[mid + j];
    L[leftN] = Integer.MAX_VALUE;
    R[rightN] = Integer.MAX_VALUE;
    int i = 0;
    int j = 0;
    for(int k = low - 1; k < high; k++) {
      if(L[i] <= R[j]) {
        A[k] = L[i];
        i = i + 1;
      }
      else {
        A[k] = R[j];
        j = j + 1;
      }
    }
  }


  public static void main(String[] args) {
    for(int i : mergeSort(new int[] { 4, 1, 5, 2, 8, 7 }, 1, 6)) {
      System.out.print(i + " ");
    }
  }
}
