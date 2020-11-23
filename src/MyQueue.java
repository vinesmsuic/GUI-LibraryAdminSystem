public class MyQueue<E> {
  private MyLinkedList<E> list 
    = new MyLinkedList<E>();

  public void enqueue(E e) {
    // Left as an exercise
    list.addLast(e);
  }

  public E dequeue() {
    // Left as an exercise
    return list.removeFirst();
  }

  public int getSize() {
    // Left as an exercise
    return list.size();
  }

  public MyLinkedList<E> getList()
  {
    // Left as an exercise
    return list;
  }
  
  @Override
  public String toString() {
    return "Queue: " + list.toString();
  }
}