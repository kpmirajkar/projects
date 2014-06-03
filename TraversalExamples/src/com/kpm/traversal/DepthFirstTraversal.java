package com.kpm.traversal;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstTraversal {
  private final Graph graph;

  private final List<Node> visitedNodes;


  public DepthFirstTraversal(Graph graph) {
    this.graph = graph;
    visitedNodes = new ArrayList<Node>();
  }


  public void traverse() {
    // Pick the root node of the graph
    Node root = graph.getRoot();
    depthTraverse(root);
    for(Node n : visitedNodes) {
      System.out.print(n.getName() + " ");
    }
  }


  private void depthTraverse(Node current) {
    System.out.println("Visiting :" + current);
    visitedNodes.add(current);
    for(Node n : current.getLinkedNodes()) {
      if(!visitedNodes.contains(n)) {
        depthTraverse(n);
        System.out.println("\tBack to: " + current);
      }
    }
  }
}
