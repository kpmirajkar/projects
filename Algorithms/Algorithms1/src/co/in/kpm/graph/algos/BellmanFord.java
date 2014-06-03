package co.in.kpm.graph.algos;

import java.util.Set;

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
    if(source != null) {
      for(Vertex v : vertices) {
        v.setKey(INFINITY);
        v.setParent(null);
      }
      source.setKey(0);
    }
  }


  private void relax(Vertex u, Vertex v) {
    int pathWeight = u.getKey() + graph.getWeight(u, v);
    if(v.getKey() > pathWeight) {
      v.setKey(pathWeight);
      v.setParent(u);
    }

  }


  private boolean algo(String vertex) {
    Vertex source = graph.getVertex(vertex);
    if(source != null) {
      initializeSingleSource(source);
      for(int i = 0; i < graph.getVertices().size() - 1; i++) {
        for(Edge edge : graph.getEdges()) {
          relax(edge.getVertex1(), edge.getVertex2());
        }
      }
      for(Edge edge : graph.getEdges()) {
        int w2 = edge.getVertex2().getKey();
        int w1 = edge.getVertex1().getKey();
        if(w2 > w1 + graph.getWeight(edge.getVertex1(), edge.getVertex2())) {
          return false;
        }
        return true;
      }
      return true;
    }
    return false;
  }


  public static void main(String[] args) {
    Graph graph = Graph.buildDirectedGraph1();
    BellmanFord bfAlgo = new BellmanFord(graph);
    if(bfAlgo.algo("S")) {
      System.out.println(graph.getVertices());
    }
    else {
      System.out.println("Has negative cycles");
    }

  }

}
