package co.in.kpm.sorting;

public class InsertionSort {

  public static int[] insertionSort(int[] input) {
    for(int j = 1; j < input.length; j++) {
      int key = input[j];
      int i = j - 1;
      while(i >= 0 && input[i] > key) {
        input[i + 1] = input[i];
        i = i - 1;
      }
      input[i + 1] = key;
    }
    return input;
  }


  public static void main(String[] args) {
    int[] input = new int[] { 5, 2, 1, 3 };
    input = insertionSort(input);
    for(int i = 0; i < input.length; i++)
      System.out.println(input[i]);
  }
}
