/**
 * @author: Ku Wing Fung 18075712d
 * @author: Wong Tsz Hin 18050573d
 */

public class MyQueue<E> {
  private MyLinkedList<E> list 
    = new MyLinkedList<E>();

  //Add a new element to the end of queue
  public void enqueue(E e) {
    list.addLast(e);
  }

  //Remove and return the first element of the queue
  public E dequeue() {
    return list.removeFirst();
  }

  //Return the size of the queue
  public int getSize() {
    return list.size();
  }

  //Return the list of the queue
  public MyLinkedList<E> getList() {
    return list;
  }
  
  //Return the queue as a string
  @Override
  public String toString() {
    return "Queue: " + list.toString();
  }
}