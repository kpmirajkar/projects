package com.datastructures.stacks;

public interface IStack<T> {
  boolean isEmpty();


  T pop() throws StackUnderFlowException;


  void push(T t) throws StackOverFlowException;


  T peek() throws StackUnderFlowException;


  int getCurrentSize();
}
