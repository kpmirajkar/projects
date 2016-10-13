package co.in.kpm.graph.algos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import co.in.kpm.graph.model.Edge;
import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

public class Kruskal {
  private final Set<Set<Vertex>> nodeSets = new HashSet<>();
  private final Graph graph;

  private final Set<Edge> mst = new LinkedHashSet<>();

  public Kruskal(final Graph graph) {
    this.graph = graph;
  }

  public void algo() {
    makeSet();

    graph.sortEdgesByIncreasingWeights();

    for (Edge e : graph.getEdges()) {
      System.out.println("E: " + e);
      Set<Vertex> set1 = findSet(e.getVertex1());
      Set<Vertex> set2 = findSet(e.getVertex2());
      System.out.println("\tSet1=" + set1);
      System.out.println("\tSet2=" + set2);
      if (set1 != set2) {
        mst.add(e);
        union(set1, set2);
      }
    }
  }

  private void makeSet() {
    for (Vertex node : graph.getVertices()) {
      Set<Vertex> set = new HashSet<>();
      set.add(node);
      nodeSets.add(set);
    }
  }

  private Set<Vertex> findSet(Vertex node) {
    Iterator<Set<Vertex>> iterator = nodeSets.iterator();
    while (iterator.hasNext()) {
      Set<Vertex> set = iterator.next();
      if (set.contains(node)) {
        return set;
      }
    }
    return null;
  }

  private void union(Set<Vertex> set1, Set<Vertex> set2) {
    System.out.println("\t\t\tBefore union: " + nodeSets);
    nodeSets.remove(set1);
    nodeSets.remove(set2);
    set2.addAll(set1);
    nodeSets.add(set2);
    System.out.println("\t\t\tAfter  union: " + nodeSets);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Edge edge : mst) {
      sb.append(edge.toString() + '\n');
    }
    return sb.toString();
  }

  private void printTree() {
    System.out.println("\nForest:");
    for (Set<Vertex> set : nodeSets) {
      System.out.println(set);
    }
  }

  public static void main(String[] args) {
    Graph graph = Graph.buildGraph2();
    Kruskal kruskal = new Kruskal(graph);
    kruskal.algo();

    kruskal.printTree();
  }
}
