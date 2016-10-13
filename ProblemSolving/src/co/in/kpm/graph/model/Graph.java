package co.in.kpm.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
  private final String name;

  private List<Edge> edges = new ArrayList<>();

  private Set<Vertex> vertices = new LinkedHashSet<>();

  private Map<Vertex, List<Edge>> adjacentEdgeMap = new LinkedHashMap<>();


  public Graph(final String name) {
    this.name = name;
  }


  public void addEdge(String vertex1, String vertex2, int weight) {
    Vertex v1 = null;
    Vertex v2 = null;
    if(!vertices.contains(getVertex(vertex1))) {
      v1 = new Vertex(null, vertex1, weight);
      vertices.add(v1);
    }
    else {
      v1 = getVertex(vertex1);
    }

    if(!vertices.contains(getVertex(vertex2))) {
      v2 = new Vertex(null, vertex2, weight);
      vertices.add(v2);
    }
    else {
      v2 = getVertex(vertex2);
    }
    Edge edge = new Edge(v1, v2, weight);
    edges.add(edge);

    List<Edge> list = adjacentEdgeMap.get(v1);
    if(list == null) {
      list = new ArrayList<>();
    }
    list.add(edge);
    adjacentEdgeMap.put(v1, list);

    list = adjacentEdgeMap.get(v2);
    if(list == null) {
      list = new ArrayList<>();
    }
    list.add(edge);
    adjacentEdgeMap.put(v2, list);
  }


  public List<Edge> getEdges() {
    return Collections.unmodifiableList(edges);
  }


  public Set<Vertex> getVertices() {
    return vertices;
  }


  public int getWeight(Vertex v1, Vertex v2) {
    for(Edge edge : edges) {
      if(edge.getVertex1().equals(v1) && edge.getVertex2().equals(v2)) {
        return edge.getWeight();
      }
    }
    return Integer.MAX_VALUE;
  }


  public List<Edge> getAdjacentEdges(Vertex vertex) {
    return Collections.unmodifiableList(adjacentEdgeMap.get(vertex));
  }


  public Set<Edge> getAdjacentOutgoingEdges(Vertex vertex) {
    List<Edge> allAdjacentEdges = adjacentEdgeMap.get(vertex);
    Set<Edge> outgoingEdges = new HashSet<>();
    for(Edge edge : allAdjacentEdges) {
      if(edge.getVertex1().equals(vertex)) {
        outgoingEdges.add(edge);
      }
    }
    return Collections.unmodifiableSet(outgoingEdges);
  }


  public List<Vertex> getAdjacentVertices(Vertex vertex) {
    List<Edge> adjacentEdges = getAdjacentEdges(vertex);
    // System.out.println("\t\t " + vertex + ": adjacent Edges :" +
    // adjacentEdges);
    List<Vertex> adjacentVertices = new ArrayList<>();
    for(Edge edge : adjacentEdges) {
      adjacentVertices.add(edge.getOtherVertex(vertex));
    }
    System.out.println("\t adjacent Vertices for " + vertex + "   :" + adjacentVertices);
    System.out.println();
    return Collections.unmodifiableList(adjacentVertices);
  }


  public List<Vertex> getAdjacentOutgoingVertices(Vertex vertex) {
    Set<Edge> adjacentEdges = getAdjacentOutgoingEdges(vertex);
    // System.out.println("\t\t " + vertex + ": adjacent Edges :" +
    // adjacentEdges);
    List<Vertex> adjacentOutgoingVertices = new ArrayList<>();
    for(Edge edge : adjacentEdges) {
      adjacentOutgoingVertices.add(edge.getOtherVertex(vertex));
    }
    System.out.println("\t adjacent Vertices for " + vertex + "   :" + adjacentOutgoingVertices);
    System.out.println();
    return Collections.unmodifiableList(adjacentOutgoingVertices);
  }


  public int getWeight(Edge edge) {
    for(Edge e : edges) {
      if(edge.equals(e)) {
        return e.getWeight();
      }
    }
    return -1;
  }


  public Vertex getVertex(String vertex) {
    for(Vertex v : vertices) {
      if(v.getName().equals(vertex)) {
        return v;
      }
    }
    return null;
  }


  public void sortEdgesByIncreasingWeights() {
    Collections.sort(edges, new Comparator<Edge>() {
      @Override
      public int compare(Edge o1, Edge o2) {
        if(o1.getWeight() < o2.getWeight()) {
          return -1;
        }
        else if(o1.getWeight() > o2.getWeight()) {
          return 1;
        }
        return 0;

      }
    });
  }


  public String getName() {
    return name;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(Edge e : edges) {
      sb.append(e.toString() + '\n');
    }
    return sb.toString();
  }


  public static void main(String[] args) {
    buildGraph1();
  }


  /*
   * Graph1 in graphs.txt
   */
  public static Graph buildGraph1() {
    Graph g = new Graph("Undirected graph For Kruskal Algorithm");
    g.addEdge("A", "E", 5);
    g.addEdge("A", "D", 3);
    g.addEdge("A", "H", 7);
    g.addEdge("A", "B", 6);
    g.addEdge("D", "E", 9);
    g.addEdge("D", "C", 12);
    g.addEdge("C", "B", 20);
    g.addEdge("B", "F", 15);
    g.addEdge("F", "G", 17);
    g.addEdge("G", "H", 1);

    g.sortEdgesByIncreasingWeights();

    System.out.println(g);

    return g;
  }


  /**
   * Graph2 in graphs.txt
   */
  public static Graph buildGraph2() {
    Graph g = new Graph("Undirected graph For Prim's Algorithm");
    g.addEdge("A", "B", 4);
    g.addEdge("A", "H", 8);

    g.addEdge("B", "C", 8);
    g.addEdge("B", "H", 11);

    g.addEdge("C", "D", 7);
    g.addEdge("C", "I", 2);
    g.addEdge("C", "F", 4);

    g.addEdge("D", "E", 9);
    g.addEdge("D", "F", 14);

    g.addEdge("E", "F", 10);

    g.addEdge("F", "G", 2);

    g.addEdge("G", "I", 6);
    g.addEdge("G", "H", 1);

    g.addEdge("H", "I", 7);

    System.out.println(g);

    return g;
  }


  public static Graph buildDirectedGraph1() {
    Graph g = new Graph("Directed graph For Bellman-Ford Algorithm");
    g.addEdge("S", "T", 6);
    g.addEdge("S", "Y", 7);

    g.addEdge("T", "X", 5);
    g.addEdge("T", "Z", -4);
    g.addEdge("T", "Y", 8);

    g.addEdge("X", "T", -2);

    g.addEdge("Y", "X", -3);
    g.addEdge("Y", "Z", 9);

    g.addEdge("Z", "S", 2);
    g.addEdge("Z", "X", 7);

    return g;
  }


  public static Graph buildDirectedGraph2() {
    Graph g = new Graph("Directed graph For Dijkstra's Algorithm");
    g.addEdge("S", "T", 10);
    g.addEdge("S", "Y", 5);

    g.addEdge("T", "X", 1);
    g.addEdge("T", "Y", 2);

    g.addEdge("X", "Z", 4);

    g.addEdge("Y", "T", 3);
    g.addEdge("Y", "X", 9);
    g.addEdge("Y", "Z", 2);

    g.addEdge("Z", "S", 7);
    g.addEdge("Z", "X", 6);

    return g;
  }


  public static Graph buildDirectedGraph3() {
    Graph g = new Graph("Directed graph For Dijkstra's Algorithm with Cities in Romania");
    g.addEdge("Oradea", "Zerind", 71);
    g.addEdge("Zerind", "Arad", 75);

    g.addEdge("Oradea", "Sibiu", 151);
    g.addEdge("Arad", "Sibiu", 140);
    g.addEdge("Sibiu", "Fagaras", 99);
    g.addEdge("Sibiu", "Rimnicu Vilcea", 80);
    g.addEdge("Rimnicu Vilcea", "Craiova", 99);
    g.addEdge("Rimnicu Vilcea", "Pitesti", 99);
    g.addEdge("Fagaras", "Bucharest", 211);
    g.addEdge("Arad", "Timisora", 118);
    g.addEdge("Timisora", "Dobreta", 256);

    g.addEdge("Dobreta", "Craiova", 120);
    g.addEdge("Craiova", "Pitesti", 138);
    g.addEdge("Pitesti", "Bucharest", 101);

    return g;
  }

}
