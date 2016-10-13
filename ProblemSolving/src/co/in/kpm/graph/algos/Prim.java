package co.in.kpm.graph.algos;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

public class Prim {
  private final Graph graph;
  private static final int INFINITY = Integer.MAX_VALUE;

  public Prim(final Graph graph) {
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

    Set<Vertex> mst = new LinkedHashSet<>();
    mst.add(rootVertex);

    Queue<Vertex> vertices = new LinkedList<>();
    vertices.offer(rootVertex);
    while (!vertices.isEmpty()) {
      Vertex u = vertices.poll();
      List<Vertex> adjacentVertices = graph.getAdjacentVertices(u);
      for (Vertex v : adjacentVertices) {
        int edgeWeight = graph.getWeight(u, v);
        if (!mst.contains(v) && edgeWeight < v.getKey()) {
          v.setParent(u);
          v.setKey(edgeWeight);
          mst.add(v);
          vertices.offer(v);
        }
      }
    }
    System.out.println(mst);
  }

  public static void main(String[] args) {
    Graph graph = Graph.buildGraph2();
    Prim prim = new Prim(graph);
    prim.algo("A");
  }
}