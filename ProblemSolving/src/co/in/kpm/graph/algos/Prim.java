package co.in.kpm.graph.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

public class Prim {
  private final Graph graph;
  private static final int INFINITY = Integer.MAX_VALUE;
  private final List<Vertex> minWeightVertices = new ArrayList<>();


  public Prim(final Graph graph) {
    this.graph = graph;
  }


  private void algo(String root) {
    Vertex rootVertex = graph.getVertex(root);

    Set<Vertex> vertices = graph.getVertices();
    if(rootVertex != null) {
      for(Vertex v : vertices) {
        v.setKey(INFINITY);
        v.setParent(null);
      }
      rootVertex.setKey(0);
    }

    minWeightVertices.addAll(vertices);
    System.out.println(minWeightVertices);
    Set<Vertex> mst = new LinkedHashSet<>();
    mst.add(rootVertex);
    while(!minWeightVertices.isEmpty()) {
      System.out.println("\t\t\t All vertices :" + graph.getVertices());
      System.out.println("\t\t\t Before poll  :" + minWeightVertices);
      Collections.sort(minWeightVertices);
      Vertex u = minWeightVertices.remove(0);
      System.out.println("\t\t\t After poll   :" + minWeightVertices);

      for(Vertex v : graph.getAdjacentVertices(u)) {
        int edgeWeight = graph.getWeight(u, v);
        if(minWeightVertices.contains(v) && edgeWeight < v.getKey()) {
          v.setParent(u);
          v.setKey(edgeWeight);
          mst.add(v);
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
