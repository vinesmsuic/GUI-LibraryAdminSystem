/**
 * @author: Ku Wing Fung 18075712d
 * @author: Wong Tsz Hin 18050573d
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoreUILayer extends JDialog {
    
    Book targetBook;

    JPanel frameHolder = new JPanel();
    JPanel btnPanel = new JPanel();
    JButton jbtBorrow = new JButton("Borrow");
    JButton jbtReturn = new JButton("Return");
    JButton jbtReserve = new JButton("Reserve");
    JButton jbtWaitingQueue = new JButton("Waiting Queue");
    JTextArea bookInfo = new JTextArea();
    JTextArea sysMessage = new JTextArea();

    public MoreUILayer(Book bk) {
        targetBook = bk;
        refreshInfo();
        
        //Set focus to this window, so user cannot interact with the other window
        setModal(true);

        //Configuring the user interface
        frameHolder.setLayout(new BorderLayout(5,5));
        bookInfo.setEditable(false);
        frameHolder.add(bookInfo, BorderLayout.NORTH);
        btnPanel.add(jbtBorrow);
        btnPanel.add(jbtReturn);
        btnPanel.add(jbtReserve);
        btnPanel.add(jbtWaitingQueue);
        frameHolder.add(btnPanel, BorderLayout.CENTER);
        sysMessage.setEditable(false);
        frameHolder.add(sysMessage, BorderLayout.SOUTH);
        
        add(frameHolder);
        this.setTitle(bk.getTitle());
        this.setSize(500, 400);
        this.setLocationRelativeTo(null); // Center the frame 
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        ButtonListener jbtlistener = new ButtonListener();
        jbtBorrow.addActionListener(jbtlistener);
        jbtReturn.addActionListener(jbtlistener);
        jbtReserve.addActionListener(jbtlistener);
        jbtWaitingQueue.addActionListener(jbtlistener);
        
    }

    //Configure the button listener to call the corresponding functions
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("" + ((JButton) e.getSource()).getText());   //showing the button pressed for debugging purpose
            if (e.getSource()==jbtBorrow) {
            	jbtBorrowPerformed();
            } else if (e.getSource()==jbtReturn) {
            	jbtReturnPerformed();	
            } else if (e.getSource()==jbtReserve) {
            	jbtReservePerformed();
            } else if (e.getSource()==jbtWaitingQueue) {
            	jbtWaitingQueuePerformed();
            }
        }
    }
    
    //Setting the newest information of the book for display, and reset the button status of the user interface
    private void refreshInfo() {
        String info = "ISBN: " + targetBook.getISBN() + "\nTitle: " + targetBook.getTitle() + "\nAvailable: " + targetBook.getAvailable() +"\n";
        bookInfo.setText(info);
        boolean available = targetBook.isAvailable();
        jbtBorrow.setEnabled(available);
        jbtReturn.setEnabled(!available);
        jbtReserve.setEnabled(!available);
        jbtWaitingQueue.setEnabled(!available);
    }

    //Perform the borrow action of the book
    private void jbtBorrowPerformed() {
        targetBook.setAvailable(false);
        sysMessage.setText("The book is borrowed.");
    	refreshInfo();
    }
    
    //Perform the return action of the book
    private void jbtReturnPerformed(){
    	String message = "The Book is returned.\n";
    	if(targetBook.getReservedQueue().getSize() == 0) {
    		targetBook.setAvailable(true);
    	} else {
    		message += "The book is now borrowed by " + targetBook.getReservedQueue().dequeue() + ".";
    	}
    	sysMessage.setText(message);
    	refreshInfo();
    }
    
    //Ask for the input of a name and mark it as a reserver of the book
    private void jbtReservePerformed() {
    	String name = JOptionPane.showInputDialog("What'your name?");
    	targetBook.getReservedQueue().enqueue(name);
    	sysMessage.setText("This book is reserved by "  + name + ".");
    }
    
    //Display the reserved queue of the book
    private void jbtWaitingQueuePerformed() {
    	sysMessage.setText(targetBook.getWaitingQueueMsg());
    }
    
    
    
}