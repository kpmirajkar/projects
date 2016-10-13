package co.in.kpm.graph.algos;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

/**
 * Single Source MST . Implementation of the algorithm as in
 * "Introduction to Algorithms" Cormen, Leiserson, Rivest, Stein. Third edition.
 * 
 * NOTE: This version is more aligned to the Algorithm as mentioned the book.
 * However I prefer Prim.java as it is more intuitive.
 * 
 * @author Krishna Murthy P Mirajkar
 *
 */

public class Prim2 {
  private final Graph graph;
  private static final int INFINITY = Integer.MAX_VALUE;

  public Prim2(final Graph graph) {
    this.graph = graph;
  }

  /**
   * Initialization of vertex key and parent. The start vertex key is set to 0.
   * 
   * @param source
   *          The source against which the shortest path algo needs to be
   *          executed.
   */
  private void initializeSingleSource(Vertex source) {
    Set<Vertex> vertices = graph.getVertices();
    if (source != null) {
      for (Vertex v : vertices) {
        v.setKey(INFINITY);
        v.setParent(null);
      }
      source.setKey(0);
    }
  }

  private void algo(String root) {
    Vertex rootVertex = graph.getVertex(root);
    initializeSingleSource(rootVertex);

    Queue<Vertex> queue = new LinkedList<>(graph.getVertices());
    while (!queue.isEmpty()) {
      Vertex u = queue.poll();
      List<Vertex> adjacentVertices = graph.getAdjacentVertices(u);
      for (Vertex v : adjacentVertices) {
        int edgeWeight = graph.getWeight(u, v);
        if (queue.contains(v) && edgeWeight < v.getKey()) {
          v.setParent(u);
          v.setKey(edgeWeight);
          System.out.println("\t after setting parent  " + u + "   :" + adjacentVertices);
        } else {
          System.out.println("\t queue contains " + v + "=" + queue.contains(v) + ", weight=" + edgeWeight);
        }
      }
      System.out.println();
    }
    printMST();
  }

  private void printMST() {
    for (Vertex vertex : graph.getVertices()) {
      System.out.println(vertex);
    }
  }

  public static void main(String[] args) {
    Graph graph = Graph.buildGraph2();
    Prim2 prim = new Prim2(graph);
    prim.algo("A");
  }
}