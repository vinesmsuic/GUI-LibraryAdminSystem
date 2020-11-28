import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InteractiveLayer extends JPanel {
	
	//Variables for setting up the user interface
	String names = "Student Name and ID: Ku Wing Fung (18075712d) \n"
			+ "Student Name and ID: Wong Tsz Hin (18050573d)";
	JTextArea Time;
	SimpleDateFormat formatter;
	JTextArea nameText = new JTextArea(names);
	
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

	JPanel textAreaLayer = new JPanel();
	JPanel btnSubLayer = new JPanel();
	JPanel tableSubLayer = new JPanel();

	//Setting up the table of display
	final String[] COL_HEADER = { "ISBN", "Title", "Available" };
	MyTableModel tModel = new MyTableModel(COL_HEADER);

	JTable jtb = new JTable(tModel);
	JScrollPane jspane = new JScrollPane(jtb);
	
	int editIndex = -1;		//Saving the index of row in the table the user is clicking

	//Variable to store the frame of the application
	JFrame thisFrame;

	MyLinkedList<Book> bookLinkedList = new MyLinkedList<Book>();
	
	/* Variable to store the status of display order for the display all buttons
	True = ascending order    False = descending order */
	boolean ISBN_ASC = true;
    boolean Title_ASC = true;

	//Constructor of this interface
	public InteractiveLayer(JFrame thisFrame) {
		
		//Saving the frame of the application for later usage
		this.thisFrame = thisFrame;

		//Loading saved data of the book linkedlist into the system, and display it
		clearTable();
		bookLinkedList = readCSV();
		for (int i = 0; i < bookLinkedList.size(); i++) {
			Book bk = bookLinkedList.get(i);
			tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
		}		

		//Wider input field
		inputISBN.setPreferredSize(new Dimension(100, 20));
		inputTitle.setPreferredSize(new Dimension(100, 20));
		
		//Configure the TextAreaLayer
		formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		Time = new JTextArea(formatter.format(new Date(System.currentTimeMillis())));
		Time.setEditable(false);
		nameText.setEditable(false);

		//Set listener for the buttons
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

		//Configure the panels
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

		//Combine the panels for display
		textAreaLayer.setLayout(new GridLayout(0,1));
		textAreaLayer.add(nameText);
		textAreaLayer.add(Time);
		
		btnSubLayer.setLayout(new GridLayout(0, 1));
		btnSubLayer.add(inputPanel);
		btnSubLayer.add(btnPanel);
		btnSubLayer.add(btnPanel2);

		tableSubLayer.setLayout(new GridLayout(0, 1));
		tableSubLayer.add(jspane);

		setLayout(new BorderLayout(5,5));
		add(textAreaLayer,BorderLayout.NORTH);
		add(tableSubLayer, BorderLayout.CENTER);
		add(btnSubLayer, BorderLayout.SOUTH);

		//Set a window listener for responding to closing application
		thisFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                askSave();
            }
        });

		//Enable user to click on the table
		jtb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && jtb.getSelectedRow() != -1) {
					System.out.println("Selected row: " + jtb.getSelectedRow());		//Display in the console for debug purpose
					System.out.print("Column0: " + jtb.getValueAt(jtb.getSelectedRow(), 0).toString());
					System.out.print(" | Column1: " + jtb.getValueAt(jtb.getSelectedRow(), 1).toString());
					System.out.println(" | Column2: " + jtb.getValueAt(jtb.getSelectedRow(), 2).toString());

					String selectedISBN = jtb.getValueAt(jtb.getSelectedRow(), 0).toString();	//Display in the input fields
					String selectedTitle = jtb.getValueAt(jtb.getSelectedRow(), 1).toString();
					inputISBN.setText(selectedISBN);
					inputTitle.setText(selectedTitle);
				}
			}
		});

	}

	/* Configure the button listener to call the corresponding functions
	Basic checking is implemented to display message dialog instead of calling the 
	function if some requirements are not satisfied. */
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("" + ((JButton) e.getSource()).getText());   //showing the button pressed for debugging purpose
			String ISBN = inputISBN.getText();
			String title = inputTitle.getText();

			if (e.getSource() == jbtAdd) {	//Add book
				jbtAddBook(ISBN, title);
			} else if (e.getSource() == jbtEdit) {	
				editIndex = jbtEditBook(ISBN);
			} else if (e.getSource() == jbtSave) {	//Save changes to book
				jbtSaveBook(ISBN, title);
			} else if (e.getSource() == jbtDelete) {	//Delete book
				jbtRemoveBook(ISBN);
			} else if (e.getSource() == jbtSearch) {	//Search for book
				jbtSearchBook(ISBN, title);
			} else if (e.getSource() == jbtMore) {		//Manipulate the reservedQueue of the book
				jbtMorePerformed(ISBN);
			} else if (e.getSource() == jbtLoad) {		//Load test data
				jbtAddBook("0131450913", "HTML How to Program");
				jbtAddBook("0131857576", "C++ How to Program");
				jbtAddBook("0132222205", "Java How to Program");
			} else if (e.getSource() == jbtDisplay) {		//Display the books by adding order
				jbtDisplayAll();
			} else if (e.getSource() == jbtDisplayByISBN) {		//Display the books by ISBN order
				jbtDisplayAllByISBN();
			} else if (e.getSource() == jbtDisplayByTitle) {	//Display the books by title order
				jbtDisplayAllByTitle();
			} else if (e.getSource() == jbtExit) {		//Exit the program
				askSave();
			}
		}
	}

	/*Add the book to the table and book linked list
	Pop up message dialog if error is found */
	public void jbtAddBook(String ISBN, String title) {
		if (ISBN.equals("") || title.equals("")) {
			JOptionPane.showMessageDialog(thisFrame, "Error: Input field cannot be empty!");
		} else if (locateISBN(ISBN)!=-1) {
			JOptionPane.showMessageDialog(thisFrame,
					"Error: book " + ISBN + " exists in the current database");
		} else {
			Book bk = new Book(ISBN, title);
			bookLinkedList.add(bk);
			tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			clearTextField();
			ISBN_ASC = false;
			Title_ASC = false;
			refreshTime();
		}
	}

	/*Remove book from the table and book linked list
	Pop up message dialog if error is found */
	public void jbtRemoveBook(String ISBN) {
		if (ISBN.equals("")) {
			JOptionPane.showMessageDialog(thisFrame, "Error: Input field cannot be empty!");
		} else {
			int index = locateISBN(ISBN);
			if (index==-1) {
				JOptionPane.showMessageDialog(thisFrame,
					"Error: book " + ISBN + " does not exists in the current database");
			} else {
				for (int i=0;i<tModel.getRowCount();i++) {
					if (ISBN.equals(tModel.getValueAt(i, 0).toString())) {
						tModel.removeRow(i);
						break;
					}
				}
				bookLinkedList.remove(index);
				ISBN_ASC = false;
				Title_ASC = false;
				refreshTime();
			}
		}
	}

	/*Set the application to lock the interface and set title as the input field
	Return the index of the book being edited in the book linkedlist
	Pop up message dialog if error is found */
	public int jbtEditBook(String ISBN) {
		if (ISBN.equals("")) {
			JOptionPane.showMessageDialog(thisFrame, "Error: ISBN cannot be empty!");
			return -1;
		} else {
			int index = locateISBN(ISBN);
			if (index==-1) {
				JOptionPane.showMessageDialog(thisFrame,
					"Error: book " + ISBN + " does not exists in the current database");
			} else {
				String title = bookLinkedList.get(index).getTitle();
				inputTitle.setText(title);
				lockButton(true);
			}
			return index;
		}
	}

	/* Save the input values as changes to the book and unlock the buttons
	Pop up message dialog if error is found */
	public void jbtSaveBook(String newISBN, String newTitle) {
		if (newISBN.equals("") || newTitle.equals("")) {
			JOptionPane.showMessageDialog(thisFrame, "Error: Input field cannot be empty!");
		} else if (locateISBN(newISBN)!=-1) {
			JOptionPane.showMessageDialog(thisFrame,
					"Error: book " + newISBN + " exists in the current database");
		} else {
			String originalISBN = bookLinkedList.get(editIndex).getISBN();	//record the original ISBN for table searching
			bookLinkedList.get(editIndex).setISBN(newISBN);
			bookLinkedList.get(editIndex).setTitle(newTitle);
			for (int i=0;i<tModel.getRowCount();i++) {		//Update the book information display, if the book existed in the table
				if (tModel.getValueAt(i, 0).toString().equals(originalISBN)) {
					tModel.setValueAt(newISBN, i, 0);
					tModel.setValueAt(newTitle, i, 1);
					break;
				}
			}
			lockButton(false);
			ISBN_ASC = false;
			Title_ASC = false;
			refreshTime();
		}
	}

	//Search for books which match the information and display them on table
	private void jbtSearchBook(String ISBN, String title) {
		if (ISBN.equals("") && title.equals("")) {
			JOptionPane.showMessageDialog(thisFrame, "Error: Input field cannot be empty!");
		} else {
			clearTable();
			clearTextField();
			for (Book bk : bookLinkedList) {
				if ((!ISBN.equals(""))&&(bk.getISBN().contains(ISBN))) {
					tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
				} else if ((!title.equals(""))&&(bk.getTitle().contains(title))) {
					tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
				}
			}
		}
	}

	/* Show up a window for controlling the reserved queue of the book
	Pop up message dialog if error is found */
	private void jbtMorePerformed(String ISBN) {
		if (ISBN.equals("")) {
			JOptionPane.showMessageDialog(thisFrame, "Error: ISBN cannot be empty!");
		} else {
			int index = locateISBN(ISBN);
			if (index==-1) {
				JOptionPane.showMessageDialog(thisFrame,
				"Error: book " + ISBN + " does not exists in the current database");
			} else {
				Book targetBook = bookLinkedList.get(index);
				MoreUILayer more = new MoreUILayer(targetBook);
				more.setVisible(true);
				for (int i=0;i<tModel.getRowCount();i++) {		//Update the book information display, if the book existed in the table
					if (ISBN.equals(tModel.getValueAt(i, 0).toString())) {
						tModel.setValueAt(targetBook.getAvailable(), i, 2);
						break;
					}
				}
			}
		}
	}

	//Display all books in the book linkedlist by index
	private void jbtDisplayAll() {
		Book getBook;
		clearTable();
		for (int i = 0; i < bookLinkedList.size(); i++) {
			getBook = bookLinkedList.get(i);
			tModel.addRow(new String[] { getBook.getISBN(), getBook.getTitle(), getBook.getAvailable() });
		}
	}

	//Display all books in the book linkedlist by ISBN
	private void jbtDisplayAllByISBN() {
		Book bk;
		clearTable();
		ArrayList<Integer> Order = getSortOrder(1);
		if (ISBN_ASC) {
			for (int i = 0; i < bookLinkedList.size(); i++) {
				bk = bookLinkedList.get((int) Order.get(i));
				tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			}
		} else {
			for (int i = bookLinkedList.size() - 1; i >= 0; i--) {
				bk = bookLinkedList.get((int) Order.get(i));
				tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			}
		}
		ISBN_ASC = !ISBN_ASC;
	}

	//Display all books in the book linkedlist by title
	private void jbtDisplayAllByTitle() {
		Book bk;
		clearTable();
		ArrayList<Integer> Order = getSortOrder(2);
		if (Title_ASC) {
			for (int i = 0; i < bookLinkedList.size(); i++) {
				bk = bookLinkedList.get((int) Order.get(i));
				tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			}
		} else {
			for (int i = bookLinkedList.size() - 1; i >= 0; i--) {
				bk = bookLinkedList.get((int) Order.get(i));
				tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			}
		}
		Title_ASC = !Title_ASC;
	}

	//Lock and unlock the buttons on the user interface of the application
	private void lockButton(boolean lockedOn) {
		jbtSave.setEnabled(lockedOn);

		jbtAdd.setEnabled(!lockedOn);
		jbtEdit.setEnabled(!lockedOn);
		jbtDelete.setEnabled(!lockedOn);
		jbtSearch.setEnabled(!lockedOn);
		jbtMore.setEnabled(!lockedOn);
		jbtLoad.setEnabled(!lockedOn);
		jbtDisplay.setEnabled(!lockedOn);
		jbtDisplayByISBN.setEnabled(!lockedOn);
		jbtDisplayByTitle.setEnabled(!lockedOn);
		jbtExit.setEnabled(!lockedOn);

	}

	//Empty the text field
	public void clearTextField() {
		inputISBN.setText("");
		inputTitle.setText("");
	}

	//Clear the display table of the application
	public void clearTable() {
		System.out.println(tModel.getRowCount());
		while (tModel.getRowCount() > 0) {
			tModel.removeRow(0);
		}
	}

	/*Return the sorted list of index of books in the book linked list
	by input parameter: type = 1 for ISBN order, type = 2 for Title order */
	public ArrayList<Integer> getSortOrder(int type) {
		ArrayList<Integer> Order = new ArrayList<>();
		for (int i = 0; i < bookLinkedList.size(); i++) {
			Order.add(i);
		}
		if (type == 1) {
			Order.sort(
					(b1, b2) -> ((bookLinkedList.get(b1)).getISBN()).compareTo(((bookLinkedList.get(b2)).getISBN())));
		} else {
			Order.sort((b1, b2) -> ((bookLinkedList.get(b1)).getTitle())
					.compareToIgnoreCase(((bookLinkedList.get(b2)).getTitle())));
		}
		return Order;
	}

	/* look for the index of the book containing a certain ISBN value in the book list
	Return value -1 if the ISBN is not found in the list */
	public int locateISBN(String ISBN) {
		for (int i = 0; i < bookLinkedList.size(); i++) {
			if (bookLinkedList.get(i).getISBN().equals(ISBN)) {
				return i;
			}
		}
		return -1;
	}
	
	//Update the time display of the application
	public void refreshTime() {
		Time.setText(formatter.format(new Date(System.currentTimeMillis())));
	}
	
	//Save the information in the book linkedlist into a file
	public void saveCSV() {	
		try {
			File csvFolder = new File("log");
			if (!csvFolder.isDirectory()){
				csvFolder.mkdir();
			}
			File csvFile = new File("log/log.csv");
			FileWriter csvWriter;
			csvWriter = new FileWriter(csvFile); 			
			for (Book bk : bookLinkedList) {
				csvWriter.append(bk.getLogInfo());
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Read the data of a file and return an updated book linkedlist 
	Return an empty book linkedlist if file not found */
	public MyLinkedList<Book> readCSV() {	
		MyLinkedList<Book> csvHolder = new MyLinkedList<Book>();
		String row = "";
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("log/log.csv"));
			try {
				while ((row = csvReader.readLine()) != null) {
					String[] data = row.split("\t");
					Book bk = new Book(data[0], data[1], data[2], data[3]);
					csvHolder.add(bk);
				}
				csvReader.close();
			} catch (IOException e) {
				//e.printStackTrace();		//For debugging purpose, disabled as unnecessary
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();		//For debugging purpose, disabled as unnecessary
		}
		
		return csvHolder;
	}
	
	/* Show a dialog box which confirms the user intention of closing the application
	Ask for whether to save the current library book linkedlist for usage next time */
	public void askSave() {
		int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save the Booklist?", "Library Admin System", JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION){	
			saveCSV();
			thisFrame.dispose();
			System.exit(0);
		} else if(dialogResult == JOptionPane.NO_OPTION){	
			thisFrame.dispose();
			System.exit(0);
		}
	}
}
