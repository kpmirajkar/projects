package co.in.kpm.sorting;

public class BubbleSort {

  private static void bubbleSort(int[] input) {
    for(int i = 0; i < input.length; i++) {
      for(int j = input.length - 1; j > i ; j--) {
        if(input[j] < input[j - 1]) {
          int tmp = input[j];
          input[j] = input[j - 1];
          input[j - 1] = tmp;
        }
      }
    }
  }


  public static void main(String[] args) {
    int[] input = new int[] { 9, 1, 5, 4, 2, 6, 7, 3, 10 };
    bubbleSort(input);
    for(int i : input) {
      System.out.print(i + " ");
    }
  }
}
