import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractiveLayer extends JPanel {
	
	JPanel inputPanel = new JPanel();
	JLabel jlISBN = new JLabel("ISBN:");
	JTextField inputISBN = new JTextField();
	JLabel jlTitle = new JLabel("Title:");
	JTextField inputTitle = new JTextField();

	JPanel btnPanel = new JPanel();
	JButton jbtAdd = new JButton("Add");
	JButton jbtEdit = new JButton("Edit");
	JButton jbtSave = new JButton("Save");
	JButton jbtDelete = new JButton("Delete");
	JButton jbtSearch = new JButton("Search");
	JButton jbtMore = new JButton("More>>");
	
	JPanel btnPanel2 = new JPanel();
	JButton jbtLoad = new JButton("Load Test Data");
	JButton jbtDisplay = new JButton("Display All");
	JButton jbtDisplayByISBN = new JButton("Display All by ISBN");
	JButton jbtDisplayByTitle = new JButton("Display All by Title");
	JButton jbtExit = new JButton("Exit");
	
	JPanel btnSubLayer = new JPanel();
	JPanel tableSubLayer = new JPanel();
	
	final String[] COL_HEADER = {"ISBN", "Title", "Available"};
	MyTableModel tModel = new MyTableModel(COL_HEADER);
	
	JTable jtb = new JTable(tModel);
	JScrollPane jspane = new JScrollPane(jtb);
	
	MyLinkedList<Book> bookLinkedList = new MyLinkedList<Book> ();
	
	public InteractiveLayer() {
		
		//Wider Input Field
		inputISBN.setPreferredSize(new Dimension(100, 20));
		inputTitle.setPreferredSize(new Dimension(100, 20));
		
		//Set Listener for EVERY BUTTON
		ButtonListener listener = new ButtonListener();
		jbtAdd.addActionListener(listener);
		jbtEdit.addActionListener(listener);
		jbtSave.addActionListener(listener);
		jbtDelete.addActionListener(listener);
		jbtSearch.addActionListener(listener);
		jbtMore.addActionListener(listener);
		jbtLoad.addActionListener(listener);
		jbtDisplay.addActionListener(listener);
		jbtDisplayByISBN.addActionListener(listener);
		jbtDisplayByTitle.addActionListener(listener);
		jbtExit.addActionListener(listener);
		
		//Add things into Panels
		inputPanel.add(jlISBN);
		inputPanel.add(inputISBN);
		inputPanel.add(jlTitle);
		inputPanel.add(inputTitle);
		
		btnPanel.add(jbtAdd);
		btnPanel.add(jbtEdit);
		btnPanel.add(jbtSave);
		btnPanel.add(jbtDelete);
		btnPanel.add(jbtSearch);
		btnPanel.add(jbtMore);
		
		btnPanel2.add(jbtLoad);
		btnPanel2.add(jbtDisplay);
		btnPanel2.add(jbtDisplayByISBN);
		btnPanel2.add(jbtDisplayByTitle);
		btnPanel2.add(jbtExit);
		
		jbtSave.setEnabled(false);
		
		//Finally Pack the Panels and show
		btnSubLayer.setLayout(new GridLayout(0, 1));
		btnSubLayer.add(inputPanel);
		btnSubLayer.add(btnPanel);
		btnSubLayer.add(btnPanel2);
		
		tableSubLayer.setLayout(new GridLayout(0,1));
		tableSubLayer.add(jspane);

		setLayout(new GridLayout(2, 1));
		add(tableSubLayer);
		add(btnSubLayer);
		
		
		//Enable User to click on the table
		jtb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting() && jtb.getSelectedRow() != -1) {
					System.out.println("Selected row: "+ jtb.getSelectedRow());
					System.out.print("Column0: " + jtb.getValueAt(jtb.getSelectedRow(),0).toString());
					System.out.print(" | Column1: " + jtb.getValueAt(jtb.getSelectedRow(),1).toString());
					System.out.println(" | Column2: " + jtb.getValueAt(jtb.getSelectedRow(),2).toString());
					String selectedISBN = jtb.getValueAt(jtb.getSelectedRow(),0).toString();
					//Do something...
				}
			}
		});
	
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("" + ((JButton) e.getSource()).getText());
			
			if(e.getSource() == jbtAdd) {
				String ISBN = inputISBN.getText();
				String title = inputTitle.getText();
				JOptionPane.showMessageDialog(new UI().getFrame(), "FUCK YOU", "Fuck you ok?", JOptionPane.ERROR_MESSAGE);
			} 
			else if (e.getSource() == jbtEdit) {
				
			} 
			else if (e.getSource() == jbtSave) {
				
			} 
			else if (e.getSource() == jbtDelete) {
				
			} 
			else if (e.getSource() == jbtSearch) {
				
			} 
			else if (e.getSource() == jbtMore) {
				
			} 
			else if (e.getSource() == jbtLoad) {
				addBook("0131450913","HTML How to Program");
				addBook("0131857576","C++ How to Program");
				addBook("0132222205","Java How to Program");
				
			} 
			else if (e.getSource() == jbtDisplay) {
				
			} 
			else if (e.getSource() == jbtDisplayByISBN) {
				
			} 
			else if (e.getSource() == jbtDisplayByTitle) {
				
			} 
			else if (e.getSource() == jbtExit) {
				System.exit(0);
			} 
		}
	}
	
	public void clearTextField() {
		inputISBN.setText("");
		inputTitle.setText("");	
	}
	
	public void addBook(String ISBN, String title) {
		Book bk = new Book(ISBN, title);
		bookLinkedList.add(bk);
		tModel.addRow(new String[] {bk.getISBN(), bk.getTitle(), bk.getAvailable()});
		clearTextField();
	}
	
	public void removeBook(String ISBN) {
		
	}

}
