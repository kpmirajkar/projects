package co.in.kpm.graph.model;

public class Vertex implements Comparable<Vertex> {
  private Vertex parent;
  private final String name;
  private int key;


  public Vertex(Vertex parent, String name, int key) {
    super();
    this.parent = parent;
    this.name = name;
    this.key = key;
  }


  public Vertex getParent() {
    return parent;
  }


  public String getName() {
    return name;
  }


  public void setParent(Vertex parent) {
    this.parent = parent;
  }


  public void setKey(int key) {
    this.key = key;
  }


  @Override
  public int hashCode() {
    return name.hashCode();
  }


  @Override
  public boolean equals(Object obj) {
    if(obj == null) {
      return false;
    }
    if(obj.getClass() != getClass()) {
      return false;
    }
    if(!(obj instanceof Vertex)) {
      return false;
    }
    Vertex other = (Vertex)obj;
    if(name.equals(other.name)) {
      return true;
    }
    return super.equals(obj);
  }


  @Override
  public String toString() {
    return name + "(" + key + ")";
  }


  public int getKey() {
    return key;
  }


  @Override
  public int compareTo(Vertex o) {
    return key - o.key;
  }

}
