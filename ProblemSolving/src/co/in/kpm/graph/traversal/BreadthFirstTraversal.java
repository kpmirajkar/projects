package co.in.kpm.graph.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstTraversal {
  private final SimpleGraph graph;

  private final List<Node> result;

  private final Queue<Node> queue;


  public BreadthFirstTraversal(SimpleGraph graph) {
    this.graph = graph;
    result = new ArrayList<Node>();
    queue = new LinkedList<Node>();
  }


  public void traverse() {
    // Pick the root node of the graph
    Node root = graph.getRoot();    
    breadthTraverse(root);
    for(Node n : result) {
      System.out.print(n.getName() + " ");
    }
  }


  private void breadthTraverse(Node current) {
    System.out.println("Visiting :" + current);
    for(Node n : current.getLinkedNodes()) {
      System.out.println("\tIterating" + n);
      if(!result.contains(n)) {
        System.out.println("\t\tAdding :");
        result.add(n);
        queue.add(n);
      }
    }
    if(queue.size() == 0) {
      System.out.println("Traversal complete");
      return;
    }
    else {
      breadthTraverse(queue.poll());
    }
  }

  public static void main(String[] args) {
    SimpleGraph graph = new SimpleGraph("Simple Non directed Graph");
    for (Node node : graph.getNodes()) {
      System.out.print(node.getName() + " ");
    }
    System.out.println();
    BreadthFirstTraversal breadthFirst = new BreadthFirstTraversal(graph);
    breadthFirst.traverse();
  }
}
