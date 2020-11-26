public class Book{
    private String title; // store the title of the book
    private String ISBN; // store the ISBN of the book
    private boolean available; // keep the status of whether the book is available;
    // initially should be true
    private MyQueue<String> reservedQueue; // store the queue of waiting list
    
    public Book(){
        available = true;
        reservedQueue = new MyQueue<String>();
    }
    
    public Book(String ISBN, String title){
    	this.ISBN = ISBN;
    	this.title = title;
        available = true;
        reservedQueue = new MyQueue<String>();
    }
    
    public Book(String ISBN, String title, String available){
    	this.ISBN = ISBN;
    	this.title = title;
    	if(available.contains("true"))
    	{
    		this.available = true;
    	}else {
    		this.available = false;
    	}
        reservedQueue = new MyQueue<String>();
    }

    public String getTitle(){
        return title;
    }

    public String getISBN(){
        return ISBN;
    }

    public boolean isAvailable(){
        return available;
    }
    
    public String getAvailable() {
    	if(available)
    		return "true";
    	return "false";
    }

    public MyQueue<String> getReservedQueue(){
        return reservedQueue;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }

    public void setReservedQueue(MyQueue<String> reservedQueue){
        this.reservedQueue = reservedQueue;
    }
    
	//Using tab as separator
	public String getLogInfo() {
		String tabString = this.getISBN();
		tabString += "\t";
		tabString += this.getTitle();
		tabString += "\t";
		tabString += this.getAvailable();
		tabString += "\n";
		return tabString;
	}
}
