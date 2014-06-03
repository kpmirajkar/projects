package com.kpm.traversal;

public class Main {

  public static void main(String[] args) {
    Graph graph = new Graph("My Graph");
    for(Node node : graph.getNodes()) {
      System.out.print(node.getName()+" ");
    }
    System.out.println();
//    DepthFirstTraversal depthFirstTraversal = new DepthFirstTraversal(graph);
//    depthFirstTraversal.traverse();
//    BreadthFirstTraversal breadthFirst = new BreadthFirstTraversal(graph);
//    breadthFirst.traverse();
    DepthFirstTraversal1 depthFirst2 = new DepthFirstTraversal1(graph);
    depthFirst2.traverse();
    System.out.println("\u00A9");
  }
}
