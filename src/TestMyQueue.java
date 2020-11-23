public class TestMyQueue {
  public static void main(String[] args) {
     // Create a queue
    MyQueue<String> queue = new MyQueue<>();

    // Add elements to the queue
    queue.enqueue("Tom"); // Add Tom to the queue
    System.out.println("(1) " + queue);

    queue.enqueue("Susan"); // Add Susan to the queue
    System.out.println("(2) " + queue);

    queue.enqueue("Kim"); // Add Kim to the queue
    queue.enqueue("Michael"); // Add Michael to the queue
    System.out.println("(3) " + queue);

    // Remove elements from the queue
    System.out.println("(4) " + queue.dequeue());
    System.out.println("(5) " + queue.dequeue());
    System.out.println("(6) " + queue);

    System.out.println("The size of the queue is " + queue.getSize());

  }
}