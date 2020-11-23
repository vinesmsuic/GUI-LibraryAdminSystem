import java.util.Scanner;

//Test the extra methods in MyLinkedList e.g. contains(), get(), indexOf(), lastIndexOf()
public class TestMyLinkedListExtra {
	public static void main(String[] args) {
		String[] names = {"Tom", "Susan", "Kim", "George", "Peter", 
				"Jean", "George", "Jane", "Denise", "Jenny", "Susan", "Kathy", "Jane"};
		
		MyList<String> list = new MyLinkedList<>(names);   

		System.out.println(list);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a name: ");
		String name = input.next();

		System.out.print("Enter an index: ");
		int index = input.nextInt();

		System.out.println(name + " is in the list? " + list.contains(name));
		System.out.println("name at index " + index + " is " + list.get(index));	
		System.out.println(name + " is at index " + list.indexOf(name));
		System.out.println(name + " is at last index " + list.lastIndexOf(name));
		list.set(index, name);
	}
}
