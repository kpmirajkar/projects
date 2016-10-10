package co.in.kpm.problems;

public class FactorialIterative {

  public static void main(String[] args) {
    int upto = 5;// maximum with long 39!
    long product = 1;
    for (int i = 1; i <= upto; i++) {
      product *= i;
    }
    System.out.println(product);
  }

}
