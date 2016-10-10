package co.in.kpm.graph.algos;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

/**
 * Single Source shortest path. Implementation of the algorithm as in
 * "Introduction to Algorithms" Cormen, Leiserson, Rivest, Stein. Third edition.
 * 
 * @author Krishna Murthy P Mirajkar
 *
 */
public class Dijkstra {
  private final Graph graph;
  private static final int INFINITY = Integer.MAX_VALUE;

  public Dijkstra(final Graph graph) {
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

  /**
   * Compute path weight from start source. Set key only if the weight is less
   * the vertex's weight
   * 
   * @param u
   * @param v
   */
  private void relax(Vertex u, Vertex v) {
    int pathWeight = u.getKey() + graph.getWeight(u, v);
    if (pathWeight < v.getKey()) {
      v.setKey(pathWeight);
      v.setParent(u);
    }
  }

  /**
   * Djikstra's Algorithm main procedure
   * 
   * @param source
   *          The start vertex against which the shortest path needs to be
   *          evaluated.
   */
  public void algo(String source) {
    Vertex sv = graph.getVertex(source);
    initializeSingleSource(sv);
    Set<Vertex> visited = new HashSet<>();
    Queue<Vertex> queue = new PriorityQueue<>();
    queue.offer(sv);
    while (!queue.isEmpty()) {
      Vertex u = queue.poll();
      List<Vertex> adjacentOutgoingVertices = graph.getAdjacentOutgoingVertices(u);
      for (Vertex v : adjacentOutgoingVertices) {
        relax(u, v);
        if (!visited.contains(v)) {
          visited.add(v);
          queue.offer(v);
        }
      }
    }
  }

  /**
   * Procedure to find the shortest path evaluated on the graph against the
   * source algorithm was executed.
   * 
   * @param target
   *          The target vertex to which the shortest path needs to be computed
   *          is to be known.
   * @return The shortest path to the target from the source against which
   *         algorithm was executed.
   */
  public Collection<Vertex> getShortestPath(String target) {
    Vertex tv = graph.getVertex(target);
    SortedSet<Vertex> path = new TreeSet<>();
    while (tv != null) {
      path.add(tv);
      tv = tv.getParent();
    }
    return path;
  }

  public static void main(String[] args) {
    Graph graph = Graph.buildDirectedGraph2();
    Dijkstra dj = new Dijkstra(graph);
    dj.algo("S");
    System.out.println(dj.getShortestPath("Z"));

    // Graph graph = Graph.buildDirectedGraph3();
    // Dijkstra dj = new Dijkstra(graph);
    // dj.algo("Oradea");
    // System.out.println(dj.getShortestPath("Bucharest"));
  }
}
