 
public class TestMyLinkedList {
  /** Main method */
  public static void main(String[] args) {
    // Create a list for strings
    MyLinkedList<String> list = new MyLinkedList<>();

    // Add elements to the list
    list.add("America"); // Add America to the list
    System.out.println("(1) " + list);

    list.add(0, "Canada"); // Add Canada to the beginning of the list
    System.out.println("(2) " + list);

    list.add("Russia"); // Add Russia to the end of the list
    System.out.println("(3) " + list);

    list.addLast("France"); // Add France to the end of the list
    System.out.println("(4) " + list);

    list.add(2, "Germany"); // Add Germany to the list at index 2
    System.out.println("(5) " + list);

    list.add(5, "Norway"); // Add Norway to the list at index 5
    System.out.println("(6) " + list);

    list.add(0, "Poland"); // Same as list.addFirst("Poland")
    System.out.println("(7) " + list);

    // Remove elements from the list
    list.remove(0); // Same as list.remove("Australia") in this case
    System.out.println("(8) " + list);

    list.remove(2); // Remove the element at index 2
    System.out.println("(9) " + list);

    list.remove(list.size() - 1); // Remove the last element
    System.out.print("(10) " + list + "\n(11) ");
    
    for (String s: list)
      System.out.print(s.toUpperCase() + " ");

    java.util.Iterator<String> iterator = list.iterator();
    System.out.println("\n(12) iterator.next(): " + iterator.next());
    System.out.println("(13) iterator.next(): " + iterator.next());
    System.out.println("(14) iterator.next(): " + iterator.next());
    iterator.remove();
    System.out.println("(15) list after iterator.remove(): " + list);
    System.out.println("Before clearing the list, the list size is " + list.size());

    list.clear();
    System.out.println("After clearing the list, the list size is " 
      + list.size());
  }
}