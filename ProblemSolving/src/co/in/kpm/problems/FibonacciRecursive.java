package co.in.kpm.problems;

public class FibonacciRecursive {
  static long n1 = 0, n2 = 1, n3 = 0;
  static int count = 10;

  public static void main(String[] args) {
    System.out.println(0);
    System.out.println(1);
    printFibonacci(count);
  }

  private static void printFibonacci(int count) {
    if (count > 0) {
      n3 = n1 + n2;
      n1 = n2;
      n2 = n3;
      System.out.println(n3);
      printFibonacci(count - 1);
    }
  }
}
