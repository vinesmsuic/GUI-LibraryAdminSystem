import java.util.Iterator;
import java.util.LinkedList;

public class TestBook {

	public static void main(String[] args) {
		MyLinkedList<Book> bookLinkedList = new MyLinkedList<Book> ();
		Book book1 = new Book();
        book1.setISBN("11111111");
        book1.setTitle("Book1");
        book1.setAvailable(true);
        bookLinkedList.add(book1);

		Book book2 = new Book();
		book2.setISBN("22222222");
		book2.setTitle("Book2");
		book2.setAvailable(true);
		bookLinkedList.add(book2);
        
		Book book3 = new Book();
		book3.setISBN("33333333");
		book3.setTitle("Book3");
		book3.setAvailable(false);
        bookLinkedList.add(book3);
        MyQueue<String> queue=book3.getReservedQueue();
        queue.enqueue("Borrower1");
        queue.enqueue("Borrower2");
        queue.enqueue("Borrower3");
               
        
		Book book4 = new Book();
		book4.setISBN("44444444");
		book4.setTitle("Book4");
		book4.setAvailable(true);
        bookLinkedList.add(book4);
        
        Iterator<Book> it = bookLinkedList.iterator();
        Book bk;
        while (it.hasNext())
        {
        	bk=it.next();
        	if (bk.isAvailable())
        		System.out.println(bk.getISBN()+" "+bk.getTitle()+": available");
        	else
        	{
        		System.out.println(bk.getISBN()+" "+bk.getTitle()+": Not available");
        		System.out.print("\tReserved queue: ");
        	       for(String name: queue.getList()) {	
        	            System.out.print(name+" ");
        	       }
        	    System.out.println();
        	}
       }

	}

}
