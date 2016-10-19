package co.in.kpm.graph.algos;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import co.in.kpm.graph.model.Edge;
import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

public class BellmanFord {
  private final Graph graph;
  private static final int INFINITY = Integer.MAX_VALUE;

  public BellmanFord(final Graph graph) {
    this.graph = graph;
  }

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

  private void relax(Vertex u, Vertex v) {
    int pathWeight = u.getKey() + graph.getWeight(u, v);
    if (v.getKey() > pathWeight) {
      v.setKey(pathWeight);
      v.setParent(u);
    }

  }

  private boolean algo(String vertex) {
    Vertex source = graph.getVertex(vertex);
    if (source != null) {
      initializeSingleSource(source);
      List<Edge> edges = graph.getEdges();
      for (int i = 0; i < graph.getVertices().size() - 1; i++) {
        System.out.println("Pass " + (i + 1));
        for (Edge edge : edges) {
          relax(edge.getVertex1(), edge.getVertex2());
          System.out.println("Relax pass :" + (i + 1));
          System.out.println(edges);
        }
      }
      for (Edge edge : edges) {
        int w2 = edge.getVertex2().getKey();
        int w1 = edge.getVertex1().getKey();
        int edgeWeight = graph.getWeight(edge.getVertex1(), edge.getVertex2());
        if (w2 > w1 + edgeWeight) {
          return false;
        }
      }
      return true;
    }
    return true;
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
    Graph graph = Graph.buildDirectedGraph1();
    BellmanFord bfAlgo = new BellmanFord(graph);
    if (bfAlgo.algo("S")) {
      System.out.println(bfAlgo.getShortestPath("Z"));
    } else {
      System.out.println("Has negative cycles");
    }
  }

}
