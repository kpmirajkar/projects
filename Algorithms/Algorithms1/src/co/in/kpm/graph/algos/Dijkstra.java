package co.in.kpm.graph.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

public class Dijkstra {
  private final Graph graph;
  private static final int INFINITY = Integer.MAX_VALUE;
  private final List<Vertex> minWeightVertices = new ArrayList<>();


  public Dijkstra(final Graph graph) {
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
    if(pathWeight < v.getKey()) {
      v.setKey(pathWeight);
      v.setParent(u);
    }
  }


  private void algo(String source) {
    Vertex sv = graph.getVertex(source);
    Set<Vertex> shortestPath = new LinkedHashSet<>();
    if(sv != null) {
      initializeSingleSource(sv);
      minWeightVertices.addAll(graph.getVertices());
      while(!minWeightVertices.isEmpty()) {
        System.out.println("\t\t\t All vertices :" + graph.getVertices());
        System.out.println("\t\t\t Before poll  :" + minWeightVertices);
        Collections.sort(minWeightVertices);
        Vertex u = minWeightVertices.remove(0);
        System.out.println("\t\t\t After poll   :" + minWeightVertices);
        shortestPath.add(u);
        for(Vertex v : graph.getAdjacentOutgoingVertices(u)) {
          relax(u, v);
        }
      }
    }
  }


  private List<Vertex> getShortestPath(String target) {
    Vertex tv = graph.getVertex(target);
    List<Vertex> path = new ArrayList<>();
    while(tv != null) {
      path.add(tv);
      tv = tv.getParent();
    }
    Collections.sort(path, new Comparator<Vertex>() {
      @Override
      public int compare(Vertex o1, Vertex o2) {
        return o1.getKey() - o2.getKey();
      }
    });
    return path;
  }


  public static void main(String[] args) {
    Graph graph = Graph.buildDirectedGraph3();
    Dijkstra dj = new Dijkstra(graph);
    // dj.findShortestPath("S", "Z");
    // dj.findShortestPath("S", "X");
    System.out.println(graph.getName());
    dj.algo("Oradea");
    System.out.println(dj.getShortestPath("Bucharest"));

    dj.algo("Oradea");
    System.out.println(dj.getShortestPath("Craiova"));
  }
}
