package co.in.kpm.graph.traversal;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstTraversal {
  private final SimpleGraph graph;

  private final List<Node> visitedNodes;

  public DepthFirstTraversal(SimpleGraph graph) {
    this.graph = graph;
    visitedNodes = new ArrayList<Node>();
  }

  public void traverse() {
    // Pick the root node of the graph
    Node root = graph.getRoot();
    depthTraverse(root);
    for (Node n : visitedNodes) {
      System.out.print(n.getName() + " ");
    }
  }

  private void depthTraverse(Node current) {
    System.out.println("Visiting :" + current);
    visitedNodes.add(current);
    for (Node node : current.getLinkedNodes()) {
      if (!visitedNodes.contains(node)) {
        depthTraverse(node);
        System.out.println("\tBack to: " + current);
      }
    }
  }

  public static void main(String[] args) {
    SimpleGraph graph = new SimpleGraph("My Graph");
    for (Node node : graph.getNodes()) {
      System.out.print(node.getName() + " ");
    }
    System.out.println();
    DepthFirstTraversal depthFirst2 = new DepthFirstTraversal(graph);
    depthFirst2.traverse();
  }
}
