package co.in.kpm.graph.traversal;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * The graph build is as follows - 
 * 
 * C-----B----F
 * |     |    |
 * |     |    |
 * D-----A    G
 *  \   / \   /
 *   \ /   \ /
 *    E     H
 *    
 * @author krishnam
 */
public class SimpleGraph {
  private final String graphName;
  // Nodes that make up the graph
  private final List<Node> nodes;


  public SimpleGraph(String graphName) {
    this.graphName = graphName;
    nodes = new ArrayList<Node>();
    buildGraph();
  }


  public List<Node> getNodes() {
    return nodes;
  }

  /*
   * Just for printing the graph nodes. 
   */
  public String getGraphName() {
    return graphName;
  }


  public Node getRoot() {
    if(nodes.size() > 0) {
      return nodes.get(0);
    }
    return null;
  }


  private void buildGraph() {
    Node nodeA = new Node("A");
    nodes.add(nodeA);
    Node nodeB = new Node("B");
    nodes.add(nodeB);
    Node nodeC = new Node("C");
    nodes.add(nodeC);
    Node nodeD = new Node("D");
    nodes.add(nodeD);
    Node nodeE = new Node("E");
    nodes.add(nodeE);
    Node nodeF = new Node("F");
    nodes.add(nodeF);
    Node nodeG = new Node("G");
    nodes.add(nodeG);
    Node nodeH = new Node("H");
    nodes.add(nodeH);

    Node.connect(nodeA, nodeB);
    Node.connect(nodeA, nodeE);
    Node.connect(nodeE, nodeD);
    Node.connect(nodeA, nodeD);
    Node.connect(nodeA, nodeH);
    Node.connect(nodeC, nodeB);
    Node.connect(nodeC, nodeD);
    Node.connect(nodeB, nodeF);
    Node.connect(nodeF, nodeG);
    Node.connect(nodeG, nodeH);
  }
}


class Node {
  private String name;
  private List<Node> linkedNodes = new ArrayList<Node>();


  public Node(String name) {
    this.name = name;
  }


  public String getName() {
    return name;
  }


  public List<Node> getLinkedNodes() {
    return linkedNodes;
  }


  public static void connect(Node node1, Node node2) {
    node1.linkedNodes.add(node2);
    node2.linkedNodes.add(node1);
  }
  
  @Override
  public String toString() {
    return name;
  }
}