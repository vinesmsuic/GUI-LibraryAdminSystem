public class Book{
    private String title; // store the title of the book
    private String ISBN; // store the ISBN of the book
    private boolean available; // keep the status of whether the book is available;
    // initially should be true
    private MyQueue<String> reservedQueue; // store the queue of waiting list
    
    //Constructor to create an empty book
    public Book(){
        available = true;
        reservedQueue = new MyQueue<String>();
    }
    
    //Constructor to set the book data with passed information
    public Book(String ISBN, String title){
    	this.ISBN = ISBN;
    	this.title = title;
        available = true;
        reservedQueue = new MyQueue<String>();
    }
    
    //Constructor to set the book data with passed information
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
    
    //Constructor to set the data passed from the log file as the book data
    public Book(String ISBN, String title, String available, String queueList){
    	this.ISBN = ISBN;
    	this.title = title;
    	if(available.contains("true"))
    	{
    		this.available = true;
    	}else {
    		this.available = false;
        }
        reservedQueue = new MyQueue<String>();
        String temp = queueList.replace("Queue: ", "");
        if (temp.length()>2){
            StringBuilder queueData = new StringBuilder(temp);
    	    queueData.deleteCharAt(queueData.length() - 1);
            queueData.deleteCharAt(0);
            String[] names = queueData.toString().split(", ");
            for(String name: names) {
            	reservedQueue.enqueue(name);
            }
        }
    	
    }

    //Return the title of the book
    public String getTitle(){
        return title;
    }

    //Return the ISBN of the book
    public String getISBN(){
        return ISBN;
    }

    //Return the available status as a boolean
    public boolean isAvailable(){
        return available;
    }
    
    //Return the available status as a string
    public String getAvailable() {
    	if(available)
    		return "true";
    	return "false";
    }

    //Return the reservedQueue as a queue
    public MyQueue<String> getReservedQueue(){
        return reservedQueue;
    }
    
    //Return the reservedQueue as a string
    public String getWaitingQueueMsg() {
    	String sysMsg = "The waiting queue: \n";
    	for (String name : this.getReservedQueue().getList()) {
    		sysMsg += name;
    		sysMsg += "\n";
    	}
    	return sysMsg;
    }

    //Set the string as the title of the book
    public void setTitle(String title){
        this.title = title;
    }

    //Set the string as the ISBN of the book
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    //Set the boolean as the available status
    public void setAvailable(boolean available){
        this.available = available;
    }

    //Set the passed queue of String as the reservedQueue
    public void setReservedQueue(MyQueue<String> reservedQueue){
        this.reservedQueue = reservedQueue;
    }
    
	//Return log String of the book
	public String getLogInfo() {
		String tabString = this.getISBN();
		tabString += "\t";  //using tab as separator
		tabString += this.getTitle();
		tabString += "\t";
		tabString += this.getAvailable();
		tabString += "\t";
		tabString += this.getReservedQueue().toString();
		tabString += "\n";
		return tabString;
	}
}
