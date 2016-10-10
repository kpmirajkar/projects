package co.in.kpm.sorting;

public class Heapsort {

  private int heapSize;


  private int left(int i) {
    return 2 * i;
  }


  private int right(int i) {
    return (2 * i) + 1;
  }


  private void maxHeapify(int A[], int i) {
    int left = left(i);
    int right = right(i);
    int largest = 0;
    if(left <= heapSize && A[left] > A[i]) {
      largest = left;
    }
    else {
      largest = i;
    }
    if(right <= heapSize && A[right] > A[largest]) {
      largest = right;
    }
    if(i != largest) {
      int tmp = A[i];
      A[i] = A[largest];
      A[largest] = tmp;
      maxHeapify(A, largest);
    }
  }


  private void buildMaxHeap(int[] A) {
    for(int i = (A.length - 1) / 2; i >= 0; i--) {
      maxHeapify(A, i);
    }
  }


  public void heapsort(int[] A) {
    heapSize = A.length - 1;
    buildMaxHeap(A);
    for(int i = A.length - 1; i >= 1; i--) {
      int temp = A[0];
      A[0] = A[i];
      A[i] = temp;
      heapSize = heapSize - 1;
      maxHeapify(A, 0);
    }
  }


  public static void main(String[] args) {
    Heapsort hs = new Heapsort();
    int[] A = new int[] { 3, 1, 5, 7, 2, 9, 45, 21, 34 };
    hs.heapsort(A);
    for(int i : A) {
      System.out.print(i + " ");
    }
  }
}
