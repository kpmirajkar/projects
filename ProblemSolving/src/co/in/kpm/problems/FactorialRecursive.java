package co.in.kpm.problems;

public class FactorialRecursive {

  private static long factorial(int n) {
    return n == 1 ? 1 : n * factorial(--n);
  }

  public static void main(String[] args) {
    System.out.println(factorial(4));
  }
}
