package co.in.kpm.problems;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Maintain a list of top N numbers in a large set of input.
 * 
 * 
 * @author Krishna Murthy P Mirajkar
 *
 */
public class MinNElement {

  private int limit = 5;// default size

  /**
   * A max priority queue. This will maintain numbers in highest to lowest
   * order.
   */
  private final Queue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

  /**
   * Removes the head of the queue when exceeds the limit thus, the highest
   * element at head is removed, ending up having only minimum elements in the
   * tail end remaining. <br>
   * <br>
   * The lowest elements found till now will be found to be in highest -> lowest
   * order.
   * 
   * @param num,
   *          Number to be checked for and added.
   */
  public void add(Integer num) {
    maxPQ.offer(num);
    if (maxPQ.size() > limit) {
      maxPQ.poll();
    }
  }

  /**
   * Set the number of elements to maintain
   * 
   * @param limit
   *          count of elements
   */
  public void setLimit(int limit) {
    this.limit = limit;
  }

  /**
   * Reverse the queue to print the element in lowest -> highest order.
   */
  public void printTopElements() {
    Stack<Integer> topElements = new Stack<>();
    while (!maxPQ.isEmpty()) {
      topElements.push(maxPQ.poll());
    }
    while (!topElements.isEmpty()) {
      System.out.println(topElements.pop());
    }
  }

  public static void main(String[] args) {
    MinNElement minN = new MinNElement();
    minN.setLimit(10);

    addRandomNumbers(100, 100000, minN, false);

    minN.printTopElements();
  }

  /**
   * Test Feed random numbers;
   * 
   * @param feedSize
   * @param seed
   * @param maxN
   * @param print
   */
  private static void addRandomNumbers(int feedSize, int seed, MinNElement maxN, boolean print) {
    Random r = new Random();
    for (int i = 0; i < feedSize; i++) {
      int nextInt = r.nextInt(seed);
      if (print) {
        System.out.println(nextInt);
      }
      maxN.add(nextInt);
    }
  }
}
