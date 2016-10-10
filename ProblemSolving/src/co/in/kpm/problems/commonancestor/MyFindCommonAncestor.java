package co.in.kpm.problems.commonancestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MyFindCommonAncestor implements FindCommonAncestor {

  public String findCommmonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2) {
    Graph graph = new Graph("Commit Graph", commitHashes, parentHashes);
    Node commonAncestor = graph.findCommonAncestor(commitHash1, commitHash2);
    return commonAncestor.getName();
  }


  public static void main(String[] args) {
    String[] commits = { "G", "F", "E", "D", "C", "B", "A" };
    String[][] parents = { { "F", "D" }, { "E" }, { "B" }, { "C" }, { "B" }, { "A" }, null };
    String commit1 = "D";
    String commit2 = "F";

    // COMPLEX case 1
    // String[] commits = { "L", "K", "J", "I", "H", "G", "F", "E", "D", "C",
    // "B", "A" };
    // String[][] parents = { { "H", "K" }, { "J" }, { "I" }, { "B" }, { "E",
    // "G" }, { "F" }, { "C" }, { "D" }, { "C" }, { "B" },
    // { "A" }, null };
    // String commit1 = "G";
    // String commit2 = "K";

    // COMPLEX case 2
    // String[] commits = { "N", "M", "L", "K", "J", "I", "H", "G", "F", "E",
    // "D", "C", "B", "A" };
    // String[][] parents = { { "J", "I", "M" }, { "L" }, { "K" }, { "B" }, {
    // "E", "G", "I" }, { "H", "B" }, { "C" }, { "F" },
    // { "C" }, { "D" }, { "C" }, { "B" }, { "A" }, null };
    // String commit1 = "E";
    // String commit2 = "G";

    MyFindCommonAncestor mfca = new MyFindCommonAncestor();
    String commonAncestor = mfca.findCommmonAncestor(commits, parents, commit1, commit2);
    System.out.println("commonAncestor : " + commonAncestor);
  }

  private static class Graph {
    private final String graphName;
    // Nodes that make up the graph
    private final List<Node> nodes;
    private final String[][] parents;
    private final String[] commits;
    private final Map<String, String[]> commitToParentMap;
    private final Map<String, Node> commitToNodeMap;


    public Graph(final String graphName, final String[] commits, final String[][] parents) {
      this.graphName = graphName;
      nodes = new ArrayList<Node>();
      commitToParentMap = new LinkedHashMap<>();
      commitToNodeMap = new HashMap<>();

      this.parents = parents;
      this.commits = commits;
      for(int i = 0; i < commits.length; i++) {
        commitToParentMap.put(this.commits[i], parents[i]);
      }
      buildGraph();
      // To see the graph built structure
      BreadthFirstTraversal bfs = new BreadthFirstTraversal(this);
      bfs.traverse();
    }


    public Node findCommonAncestor(final String commit1, final String commit2) {
      final Node first = commitToNodeMap.get(commit1);
      final Node second = commitToNodeMap.get(commit2);

      return findCommon(first, second);
    }


    private Node findCommon(final Node first, final Node second) {
      Node commonParent1 = findSplitterParent(first);
      Node commonParent2 = findSplitterParent(second);
      if(commonParent1 == commonParent2) {
        return commonParent1;
      }
      else {
        if(commonParent1.getParent().getParent() == null) {
          commonParent1 = first;
        }
        if(commonParent2.getParent().getParent() == null) {
          commonParent2 = second;
        }
        return findCommon(commonParent1, commonParent2);
      }
    }


    private Node findSplitterParent(final Node n) {
      if(n.getParent().getLinkedNodes().size() > 2) {
        return n.getParent();
      }
      return findSplitterParent(n.getParent());
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
      return commitToNodeMap.get(null);
    }


    private void buildGraph() {
      for(final String s : commits) {
        final Node node = new Node(s);
        nodes.add(node);
        commitToNodeMap.put(s, node);
      }

      commitToNodeMap.put(null, nodes.get(nodes.size() - 1));

      for(final String commit : commits) {
        final Node commitNode = commitToNodeMap.get(commit);
        final String[] parents = commitToParentMap.get(commit);
        if(parents == null) {
          final Node node = commitToNodeMap.get(null);
          node.connect(node, commitNode);
        }
        else {
          for(int j = 0; j < parents.length; j++) {
            final Node node = commitToNodeMap.get(parents[j]);
            node.connect(node, commitNode);
          }
        }
      }
    }

  }

  private static class Node {
    private final String name;
    private final Set<Node> linkedNodes = new HashSet<Node>();
    private Node parent;
    private Node child;


    public Node(String name) {
      this.name = name;
    }


    public String getName() {
      return name;
    }


    public Set<Node> getLinkedNodes() {
      return linkedNodes;
    }


    public void connect(Node parent, Node child) {
      if(parent == child) {
        child.parent = null;
        return;
      }
      child.parent = parent;
      parent.child = child;
      parent.linkedNodes.add(child);
      child.linkedNodes.add(parent);
    }


    public Node getParent() {
      return parent;
    }


    public Node getChild() {
      return child;
    }


    @Override
    public int hashCode() {
      int sum = 0;
      for(char c : name.toCharArray()) {
        sum += c;
      }
      return sum;
    }


    @Override
    public boolean equals(Object obj) {
      if(obj == null) {
        return false;
      }
      if(obj.getClass() != getClass()) {
        return false;
      }
      if(!(obj instanceof Node)) {
        return false;
      }
      else {
        Node other = (Node)obj;
        return (other.name.equals(name));
      }

    }


    @Override
    public String toString() {
      return name;
    }
  }

  static class BreadthFirstTraversal {
    private final Graph graph;

    private final List<Node> visitedNodes;

    private final Queue<Node> queue;


    public BreadthFirstTraversal(Graph graph) {
      this.graph = graph;
      visitedNodes = new ArrayList<Node>();
      queue = new LinkedList<Node>();
    }


    public void traverse() {
      // Pick the root node of the graph
      Node root = graph.getRoot();
      breadthTraverse(root);
      for(Node n : visitedNodes) {
        System.out.print(n.getName() + " ");
      }
      System.out.println("\nTraversal Complete!");
    }


    private void breadthTraverse(Node current) {
      System.out.println("Visiting :" + current);
      for(Node n : current.getLinkedNodes()) {
        System.out.println("\tIterating " + n);
        if(!visitedNodes.contains(n)) {
          System.out.println("\t\tAdding :");
          visitedNodes.add(n);
          queue.add(n);
        }
      }
      if(queue.size() == 0) {
        return;
      }
      else {
        breadthTraverse(queue.poll());
      }
    }
  }
}