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
public class MaxNElement {

  private int limit = 5;// default size

  /**
   * A min priority queue. This will maintain numbers in lowest to highest
   * order.
   */
  private final Queue<Integer> minPQ = new PriorityQueue<>((o1, o2) -> o1 - o2);

  /**
   * Removes the head of the queue when exceeds the limit thus, the smaller
   * element at head is removed, ending up having only max elements in the tail
   * end remaining. <br>
   * <br>
   * The highest elements found till now will be found to be in <br>
   * lowest -> highest order.
   * 
   * @param num,
   *          Number to be checked for and added.
   */
  public void add(Integer num) {
    if (num == null) {
      return;// null elements not allowed
    }
    minPQ.offer(num);
    if (minPQ.size() > limit) {
      minPQ.poll();
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
   * Reverse the queue to print the element in highest -> lowest order.
   */
  public void printTopElements() {
    Stack<Integer> topElements = new Stack<>();
    while (!minPQ.isEmpty()) {
      topElements.push(minPQ.poll());
    }
    while (!topElements.isEmpty()) {
      System.out.println(topElements.pop());
    }
  }

  public static void main(String[] args) {
    MaxNElement maxN = new MaxNElement();
    maxN.setLimit(5);

    addRandomNumbers(10, 10000000, maxN, false);

    maxN.printTopElements();
  }

  /**
   * Test Feed random numbers;
   * 
   * @param feedSize
   * @param seed
   * @param maxN
   * @param print
   */
  private static void addRandomNumbers(int feedSize, int seed, MaxNElement maxN, boolean print) {
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
