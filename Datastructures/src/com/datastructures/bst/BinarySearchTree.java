package com.datastructures.bst;

public class BinarySearchTree {

  private Node root;


  public Node searchIterative(int t) {
    Node x = root;
    while(x != null && t != x.key) {
      if(t < x.key) x = x.left;
      else x = x.right;
    }
    return x;
  }


  public Node searchRecursive(Node x, int t) {
    if(x == null || t == x.key) {
      return x;
    }
    if(t < x.key) return searchRecursive(x.left, t);
    else return searchRecursive(x.right, t);
  }


  public Node minimum(Node x) {
    while(x.left != null) {
      x = x.left;
      System.out.println("\t going left " + x);
    }
    return x;
  }


  public Node maximum(Node x) {
    System.out.println();
    while(x.right != null) {
      x = x.right;
      System.out.println("\t going right " + x);
    }
    return x;
  }


  public void insert(Node z) {
    Node y = null;
    Node x = root;
    while(x != null) {
      y = x;
      if(z.key < x.key) {
        x = x.left;
      }
      else {
        x = x.right;
      }
    }
    z.p = y;
    if(y == null) {
      root = z;
    }
    else if(z.key < y.key) {
      y.left = z;
    }
    else {
      y.right = z;
    }
  }


  private void transplant(Node u, Node v) {
    if(u.p == null) {
      root = v;
    }
    else if(u == u.p.left) {
      u.p.left = v;
    }
    else {
      u.p.right = v;
    }
    if(v != null) {
      u.p = v.p;
    }
  }


  public boolean delete(Node z) {
    if(z.left == null) {
      transplant(z, z.right);
    }
    else if(z.right == null) {
      transplant(z, z.left);
    }
    else {
      Node y = minimum(z.right);
      if(y.p != z) {
        transplant(y, y.right);
        y.right = z.right;
        y.right.p = y;
      }
      transplant(z, y);
      y.left = z.left;
      y.left.p = y;
    }
    return false;
  }


  public void inorderTreeWalk(Node x) {
    if(x != null) {
      inorderTreeWalk(x.left);
      System.out.print(x.key + " ");
      inorderTreeWalk(x.right);
    }
  }


  public Node successor(Node x) {
    if(x.right != null) {
      return minimum(x.right);
    }
    Node y = x.p;
    while(y != null && x == y.right) {
      y = x;
      x = x.right;
    }
    return y;
  }


  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    Node n6 = new Node(6);
    bst.insert(n6);
    Node n5 = new Node(5);
    bst.insert(n5);
    Node n4 = new Node(4);
    bst.insert(n4);
    Node n7 = new Node(7);
    bst.insert(n7);
    Node n8 = new Node(8);
    bst.insert(n8);

    bst.inorderTreeWalk(bst.root);

    System.out.println("Maximum = " + bst.maximum(bst.root));
    System.out.println("Minimum = " + bst.minimum(bst.root));

    System.out.println("Tree has 5 Iterative " + bst.searchIterative(5));
    System.out.println("Tree has 7 Recursive " + bst.searchRecursive(bst.root, 7));
    System.out.println("Tree has 1 Recursive " + bst.searchRecursive(bst.root, 1));
    System.out.println("Tree has 2 Recursive " + bst.searchRecursive(bst.root, 2));

    System.out.println("successor of " + n7 + " is " + bst.successor(n7));

    bst.delete(n7);

    bst.inorderTreeWalk(bst.root);
  }

  public static class Node {
    private Node p;
    private Node left;
    private Node right;
    private int key;


    public Node(int key) {
      this.key = key;
    }


    @Override
    public String toString() {
      return String.valueOf(key);
    }
  }
}
