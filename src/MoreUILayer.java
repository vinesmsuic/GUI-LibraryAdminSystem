import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

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
    
    private static boolean opened = false;
    
    public MoreUILayer(Book bk) {
        targetBook = bk;
        refreshInfo();

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
        this.setSize(500, 300);
        this.setLocationRelativeTo(null); // Center the frame 
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        ButtonListener jbtlistener = new ButtonListener();
        jbtBorrow.addActionListener(jbtlistener);
        jbtReturn.addActionListener(jbtlistener);
        jbtReserve.addActionListener(jbtlistener);
        jbtWaitingQueue.addActionListener(jbtlistener);
        
        opened = true;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                opened = false;
            }
        });
        
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("" + ((JButton) e.getSource()).getText());
            if (e.getSource()==jbtBorrow) {
            	targetBook.setAvailable(false);
            	refreshInfo();
            	
            } else if (e.getSource()==jbtReturn) {
            	targetBook.getReservedQueue().dequeue();
            	sysMessage.setText("This book is returned.");
            	if(targetBook.getReservedQueue().getSize() == 0) {
            		targetBook.setAvailable(true);
            	}
            	refreshInfo();
            	
            } else if (e.getSource()==jbtReserve) {
            	String name = JOptionPane.showInputDialog("What'your name?");
            	targetBook.getReservedQueue().enqueue(name);
            	sysMessage.setText("This book is reserved by "  + name + ".");

            } else if (e.getSource()==jbtWaitingQueue) {
            	sysMessage.setText(targetBook.getWaitingQueueMsg());
            }
        }
    }
    
    private void refreshInfo() {
        String info = "ISBN: " + targetBook.getISBN() + "\nTitle: " + targetBook.getTitle() + "\nAvailable: " + targetBook.getAvailable() +"\n";
        bookInfo.setText(info);
        boolean available = targetBook.isAvailable();
        jbtBorrow.setEnabled(available);
        jbtReturn.setEnabled(!available);
        jbtReserve.setEnabled(!available);
        jbtWaitingQueue.setEnabled(!available);
        if(!available) {
        	sysMessage.setText("The book is borrowed.");
        }
        else
        {
        	sysMessage.setText("");
        }
    }
    
    public static boolean existing() {
        return opened;
    }
    
}