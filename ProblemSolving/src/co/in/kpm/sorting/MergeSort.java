package co.in.kpm.sorting;

/**
 * Merge Sort algorithm with worst-case time complexity as O(nlogn). Space
 * complexity - O(n)
 * 
 * @author Alpesh Rathod
 * 
 */
public class MergeSort {

  public static void main(String[] args) {
    int[] array = new int[] { 3, 1, 5, 2, 9, 4, 10, 8, 32, 89, 20, 100, 67, 46, 58, 77, 81 };
    mergeSort(array);
  }


  static void mergeSort(int[] array) {
    int n = array.length;
    int mid = n / 2;
    // System.out.println("Mid=================== " + mid);

    // Base condition to exit out of recursion
    if(n < 2) return;

    // mid - left array size
    int[] leftArray = new int[mid];

    // (n-mid) right array size to handle odd integer array
    // Made a mistake here by initializing the array by size (mid) :(
    int[] rightArray = new int[n - mid];

    // Add values in left array till mid-1
    for(int i = 0; i <= mid - 1; i++) {
      leftArray[i] = array[i];
    }
    // Add values in right array from mid to n
    for(int i = mid; i < n; i++) {
      // (i-mid) --> so that array starts at 0
      rightArray[i - mid] = array[i];
    }

    // for (int i : leftArray) {
    // System.out.println(i);
    // }
    // System.out.println(" PASS ===================================");
    // for (int i : rightArray) {
    // System.out.println(i);
    // }

    mergeSort(leftArray);
    mergeSort(rightArray);
    merge(leftArray, rightArray, array);

  }


  static void merge(int[] leftArray, int[] rightArray, int[] newArray) {
    int i = 0, j = 0, k = 0;
    int leftLength = leftArray.length;
    int rightLength = rightArray.length;

    // Compare left and right and put the smaller value in the new array
    while(i < leftLength && j < rightLength) {
      if(leftArray[i] <= rightArray[j]) {
        newArray[k] = leftArray[i];
        i++;
      }
      else {
        newArray[k] = rightArray[j];
        j++;
      }
      k++;
    }

    // Work on the remaining elements on the left or right
    while(i < leftLength) {
      newArray[k] = leftArray[i];
      i++;
      k++;
    }
    while(j < rightLength) {
      newArray[k] = rightArray[j];
      j++;
      k++;
    }
    System.out.println("=====================Sorting Complete=================");
    for(int element : newArray) {
      System.out.println(element);
    }
  }
}
