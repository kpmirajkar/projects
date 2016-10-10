package com.datastructures.linkedlist;

public class KLinkedList<T> implements ILinkedList<T> {

  private Node<T> head = new Node<>(null, null, null);
  private int size;


  @Override
  public boolean search(final T t) {
    return searchNode(t) != null;
  }


  private Node<T> searchNode(final T t) {
    Node<T> x = head;
    while(x != null && x.value != t) {
      x = x.next;
    }
    return x;
  }


  @Override
  public void add(final T t) {
    final Node<T> node = new Node<>(null, t, head);
    head.prev = node;
    head = node;
    size++;
  }


  @Override
  public boolean remove(final T t) {
    final Node<T> x = searchNode(t);
    if(x == null) {
      return false;
    }
    if(x.prev != null) {
      x.prev.next = x.next;
    }
    else {
      head = x.next;
    }
    if(x.next != null) {
      x.next.prev = x.prev;
    }
    return false;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    Node<T> n = head;
    while(n.next != null) {
      sb.append(n.toString());
      n = n.next;
    }
    return sb.toString();
  }

  private static class Node<T> {
    private Node<T> prev;
    private Node<T> next;
    private T value;


    private Node(Node<T> previous, T t, Node<T> next) {
      this.prev = previous;
      this.value = t;
      this.next = next;
    }


    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      String comma = ", ";
      sb.append("[");
      sb.append("prev=" + ((prev != null) ? (prev.value + comma) : null + comma));
      sb.append("val=" + value + comma);
      sb.append("next=" + ((next != null) ? next.value : null));
      sb.append("]");
      return sb.toString();
    }
  }


  public static void main(String[] args) {
    testAddItems();
    testRemoveItem();
    testRemoveNonExistent();
  }


  private static void testAddItems() {
    ILinkedList<String> kLinkedList = new KLinkedList<>();
    kLinkedList.add("A");
    kLinkedList.add("B");
    kLinkedList.add("C");
    kLinkedList.add("D");
    System.out.println(kLinkedList);
  }


  private static void testRemoveItem() {
    ILinkedList<String> kLinkedList = new KLinkedList<>();
    kLinkedList.add("A");
    kLinkedList.add("B");
    kLinkedList.add("C");
    kLinkedList.add("D");
    kLinkedList.remove("A");
    kLinkedList.remove("D");
    System.out.println(kLinkedList);
  }


  private static void testRemoveNonExistent() {
    ILinkedList<String> kLinkedList = new KLinkedList<>();
    kLinkedList.add("A");
    kLinkedList.add("B");
    kLinkedList.add("C");
    boolean result = kLinkedList.remove("D");
    System.out.println("Result of Removing D=" + result + " in " + kLinkedList);
  }

}
