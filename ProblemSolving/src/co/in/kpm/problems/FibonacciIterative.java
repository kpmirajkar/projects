package co.in.kpm.problems;

public class FibonacciIterative {

  public static void main(String[] args) {
    int n1 = 0, n2 = 1, n3 = 0, count = 10;

    System.out.println(n1);
    System.out.println(n2);

    for (int i = 0; i < count; i++) {
      n3 = n1 + n2;
      System.out.println(n3);
      n1 = n2;
      n2 = n3;
    }
  }
}
