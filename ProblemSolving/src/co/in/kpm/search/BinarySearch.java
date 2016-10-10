package co.in.kpm.search;

import java.util.Arrays;

public class BinarySearch {

  public static void main(String[] args) {
    int[] input = { 1, 5, 4, 3, 2 };
    Arrays.sort(input);

    binarySearch(input, 5);
  }

  private static void binarySearch(int[] input, int key) {
    int lo = 0, hi = input.length - 1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (key == input[mid]) {
        System.out.println("Found at sort index:" + mid);
        return;
      }
      if (key < input[mid]) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    System.out.println("Key not found");
  }
}
