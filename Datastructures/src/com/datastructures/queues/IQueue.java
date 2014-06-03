package com.datastructures.queues;

public interface IQueue<T> {
  /**
   * Add item to tail of the queue. Throws exception when queue is full.
   * 
   * @return
   */
  void enqueue(T t) throws QueueFullException;


  /**
   * Returns the head of the queue. Throws exception when queue is empty.
   * 
   * @return
   */
  T dequeue() throws QueueEmptyException;


  /**
   * Returns the head of the queue. Returns null when queue is empty.
   * 
   * @return
   */
  T peek();


  /**
   * Removes the head of the queue without throwing exception on being empty
   * Returns null in that case.
   * 
   * @return
   */
  T poll();


  int currentLength();
}
