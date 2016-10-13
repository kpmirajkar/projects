package co.in.kpm.graph.algos;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import co.in.kpm.graph.model.Edge;
import co.in.kpm.graph.model.Graph;
import co.in.kpm.graph.model.Vertex;

/**
 * Kruskal's Algorithm is Generic MST which forms a forest whose vertices are
 * all those of the given graph.<br>
 * Implementation of the algorithm as in "Introduction to Algorithms" Cormen,
 * Leiserson, Rivest, Stein. Third edition.
 * 
 * @author Krishna Murthy P Mirajkar
 *
 */

public class Kruskal {
  private final Set<Set<Vertex>> nodeSets = new HashSet<>();
  private final Graph graph;

  private final Set<Edge> mst = new LinkedHashSet<>();

  public Kruskal(final Graph graph) {
    this.graph = graph;
  }

  /**
   * Main algorithm
   */
  public void algo() {
    makeSet();
    System.out.println("Initially each vertices in its own set:\n" + nodeSets + "\n");
    graph.sortEdgesByIncreasingWeights();

    System.out.println("Sorted edges in increasing weight order:\n" + graph.getEdges() + "\n");

    System.out.println("Check and add each minimum edge\n");
    for (Edge e : graph.getEdges()) {
      System.out.println("Checking edge: " + e);
      Set<Vertex> set1 = findSet(e.getVertex1());
      Set<Vertex> set2 = findSet(e.getVertex2());
      System.out.println("\t" + e.getVertex1() + " belongs to Set1=" + set1);
      System.out.println("\t" + e.getVertex2() + " belongs to Set2=" + set2);
      if (set1 != set2) {
        mst.add(e);
        union(set1, set2);
        System.out.println("MST:" + nodeSets);
      } else {
        System.out.println("Edge already belongs to MST");
      }
      System.out.println();
    }
  }

  /**
   * Make each vertex live in its own tree. In other words its own set :)
   */
  private void makeSet() {
    for (Vertex node : graph.getVertices()) {
      Set<Vertex> set = new HashSet<>();
      set.add(node);
      nodeSets.add(set);
    }
  }

  /**
   * Find the tree where this vertex is found.
   */
  private Set<Vertex> findSet(Vertex node) {
    for (Set<Vertex> set : nodeSets) {
      if (set.contains(node)) {
        return set;
      }
    }
    return null;
  }

  /**
   * Merge the trees. In other words the sets.
   * 
   * @param set1
   *          The first tree
   * @param set2
   *          The second tree
   */
  private void union(Set<Vertex> set1, Set<Vertex> set2) {
    nodeSets.remove(set1);
    nodeSets.remove(set2);
    set2.addAll(set1);
    System.out.println("union: " + set2);
    nodeSets.add(set2);
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
