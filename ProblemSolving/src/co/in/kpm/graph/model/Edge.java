package co.in.kpm.graph.model;

public class Edge {
  private final Vertex node1;
  private final Vertex node2;
  private final int weight;


  public Edge(Vertex edge1, Vertex edge2, int weight) {
    this.node1 = edge1;
    this.node2 = edge2;
    this.weight = weight;
  }


  public Vertex getVertex1() {
    return node1;
  }


  public Vertex getVertex2() {
    return node2;
  }


  public int getWeight() {
    return weight;
  }


  public Vertex getOtherVertex(Vertex me) {
    if(me.equals(node1)) {
      return node2;
    }
    else {
      return node1;
    }
  }


  @Override
  public boolean equals(Object obj) {
    if(obj == null) {
      return false;
    }
    if(obj.getClass() != getClass()) {
      return false;
    }
    if(!(obj instanceof Edge)) {
      return false;
    }
    Edge other = (Edge)obj;
    if(node1.equals(other.node1) && node2.equals(other.node2)) {
      return true;
    }
    return super.equals(obj);
  }


  @Override
  public String toString() {
    return "(" + node1 + ", " + node2 + ", " + weight + ")";
  }
}