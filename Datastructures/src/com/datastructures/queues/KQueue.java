package com.datastructures.queues;

public class KQueue<T> implements IQueue<T> {
  private int tail = 0;

  private Object[] array;

  private Object o = new Object();


  public KQueue(int size) {
    array = new Object[size];
  }


  @Override
  public void enqueue(T t) throws QueueFullException {
    synchronized(o) {
      if(tail == array.length) {
        throw new QueueFullException();
      }
      array[tail] = t;
      tail++;
    }
  }


  @Override
  public T dequeue() throws QueueEmptyException {
    final T t = peek();
    if(t == null) {
      throw new QueueEmptyException();
    }
    removeHeadFromQueue();
    return t;
  }


  @SuppressWarnings("unchecked")
  @Override
  public T peek() {
    synchronized(o) {
      if(tail == 0) {
        return null;
      }
      return (T)array[0];
    }
  }


  @Override
  public T poll() {
    final T t = peek();
    if(t == null) {
      return null;
    }
    removeHeadFromQueue();
    return t;
  }


  @Override
  public int currentLength() {
    return tail;
  }


  private void removeHeadFromQueue() {
    synchronized(o) {
      array[0] = null;
      for(int i = 0; i < tail - 1; i++) {
        array[i] = array[i + 1];
      }
      array[tail - 1] = null;
      tail--;
    }
  }


  public static void main(String[] args) {
    testDequeue();
    testPoll();
  }


  private static void testDequeue() {
    IQueue<String> queue = new KQueue<>(4);
    System.out.println("Peek item in the beginning " + queue.peek());
    System.out.println("Enqueuing A");
    queue.enqueue("A");
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Enqueuing B");
    queue.enqueue("B");
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Enqueuing C");
    queue.enqueue("C");
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Enqueuing D");
    queue.enqueue("D");
    System.out.println("Current Queue size " + queue.currentLength());

    System.out.println();

    System.out.println("Dequeuing A " + queue.dequeue());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Dequeuing B " + queue.dequeue());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Dequeuing C " + queue.dequeue());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Dequeuing D " + queue.dequeue());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
  }


  private static void testPoll() {
    IQueue<String> queue = new KQueue<>(4);
    System.out.println("Peek item in the beginning " + queue.peek());
    System.out.println("Enqueuing A");
    queue.enqueue("A");
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Enqueuing B");
    queue.enqueue("B");
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Enqueuing C");
    queue.enqueue("C");
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Enqueuing D");
    queue.enqueue("D");
    System.out.println("Current Queue size " + queue.currentLength());

    System.out.println();

    System.out.println("Dequeuing A " + queue.poll());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Dequeuing B " + queue.poll());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Dequeuing C " + queue.poll());
    System.out.println("Peek item " + queue.peek());
    System.out.println("Current Queue size " + queue.currentLength());
    System.out.println("Dequeuing D " + queue.poll());
    System.out.println("Peek item " + queue.peek());
    System.out.println("\tLastly calling again a poll " + queue.poll());
    System.out.println("Current Queue size " + queue.currentLength());
  }
}
