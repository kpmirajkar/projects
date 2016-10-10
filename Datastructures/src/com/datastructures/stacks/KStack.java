package com.datastructures.stacks;

public class KStack<T> implements IStack<T> {
  private int size;
  private int tos = -1;
  private Object[] array;

  // Private lock for synchronization
  private final Object o = new Object();


  public KStack(final int size) {
    this.size = size;
    array = new Object[size];
  }


  @Override
  public void push(final T t) throws StackOverFlowException {
    synchronized(o) {
      if(tos == size - 1) {
        throw new StackOverFlowException();
      }
      else {
        tos++;
        array[tos] = t;
      }
    }
  }


  @Override
  public T pop() throws StackUnderFlowException {
    final T t = peek();
    if(t == null) {
      throw new StackUnderFlowException();
    }
    synchronized(o) {
      array[tos] = null;
      tos--;
    }
    return t;
  }


  @Override
  public T peek() throws StackUnderFlowException {
    if(isEmpty()) {
      return null;
    }
    else {
      @SuppressWarnings("unchecked")
      final T t = (T)array[tos];
      return t;
    }
  }


  @Override
  public int getCurrentSize() {
    synchronized(o) {
      if(tos == -1) return 0;
      else {
        return size - (size - (tos + 1));
      }
    }
  }


  @Override
  public boolean isEmpty() {
    synchronized(o) {
      return tos == -1;
    }
  }


  public static void main(String[] args) {
    IStack<String> stack = new KStack<>(4);
    System.out.println("Stack is empty = " + stack.isEmpty());
    System.out.println("Pushing Shlok");
    stack.push("Shlok");
    System.out.println("Pushing Priyanka");
    stack.push("Priyanka");
    System.out.println("Pushing Krishna Murthy");
    stack.push("Krishna Murthy");
    System.out.println("Pushing XXX");
    stack.push("XXX");

    System.out.println("Stack peek is = " + stack.peek());
    System.out.println("Stack is empty = " + stack.isEmpty());
    System.out.println("Current Stack size=" + stack.getCurrentSize());

    System.out.println("Popping elements now..");
    System.out.println("Popping " + stack.pop());
    System.out.println("Stack peek is = " + stack.peek());
    System.out.println("Popping " + stack.pop());
    System.out.println("Stack peek is = " + stack.peek());
    System.out.println("Popping " + stack.pop());
    System.out.println("Stack peek is = " + stack.peek());
    System.out.println("Popping " + stack.pop());
    System.out.println("Stack peek is = " + stack.peek());

    System.out.println("Stack is empty = " + stack.isEmpty());
    System.out.println("Current Stack size=" + stack.getCurrentSize());

  }

}
