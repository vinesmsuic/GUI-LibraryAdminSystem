import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

	final String[] COL_HEADER = { "ISBN", "Title", "Available" };
	MyTableModel tModel = new MyTableModel(COL_HEADER);

	JTable jtb = new JTable(tModel);
	JScrollPane jspane = new JScrollPane(jtb);

	MyLinkedList<Book> bookLinkedList = new MyLinkedList<Book>();
	
	boolean ISBN_ASC = true;
    boolean Title_ASC = true;

	// Reserved for Editing and Saving
	int editIndex = -1;

	public InteractiveLayer() {

		// Wider Input Field
		inputISBN.setPreferredSize(new Dimension(100, 20));
		inputTitle.setPreferredSize(new Dimension(100, 20));

		// Set Listener for EVERY BUTTON
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

		// Add things into Panels
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

		// Finally Pack the Panels and show
		btnSubLayer.setLayout(new GridLayout(0, 1));
		btnSubLayer.add(inputPanel);
		btnSubLayer.add(btnPanel);
		btnSubLayer.add(btnPanel2);

		tableSubLayer.setLayout(new GridLayout(0, 1));
		tableSubLayer.add(jspane);

		setLayout(new GridLayout(2, 1));
		add(tableSubLayer);
		add(btnSubLayer);

		// Enable User to click on the table
		jtb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && jtb.getSelectedRow() != -1) {
					System.out.println("Selected row: " + jtb.getSelectedRow());
					System.out.print("Column0: " + jtb.getValueAt(jtb.getSelectedRow(), 0).toString());
					System.out.print(" | Column1: " + jtb.getValueAt(jtb.getSelectedRow(), 1).toString());
					System.out.println(" | Column2: " + jtb.getValueAt(jtb.getSelectedRow(), 2).toString());
					String selectedISBN = jtb.getValueAt(jtb.getSelectedRow(), 0).toString();
					String selectedTitle = jtb.getValueAt(jtb.getSelectedRow(), 1).toString();

					// Do something...
					inputISBN.setText(selectedISBN);
					inputTitle.setText(selectedTitle);
				}
			}
		});

	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("" + ((JButton) e.getSource()).getText());
			String ISBN = inputISBN.getText();
			String title = inputTitle.getText();

			if (e.getSource() == jbtAdd) {
				if (ISBN.equals("") || title.equals("")) {
					JOptionPane.showMessageDialog(new UI().getFrame(), "Error: Input field cannot be empty!");
				} else {
					addBook(ISBN, title);
				}

			} else if (e.getSource() == jbtEdit) {
				if (ISBN.equals("")) {
					JOptionPane.showMessageDialog(new UI().getFrame(), "Error: ISBN cannot be empty!");
				} else {
					editIndex = editBook(ISBN);
				}
			} else if (e.getSource() == jbtSave) {
				saveBook(editIndex, ISBN, title);
			} else if (e.getSource() == jbtDelete) {
				if (ISBN.equals("") || title.equals("")) {
					JOptionPane.showMessageDialog(new UI().getFrame(), "Error: Input field cannot be empty!");
				} else {
					removeBook(ISBN, title);
				}
			} else if (e.getSource() == jbtSearch) {
				if (ISBN.equals("") && title.equals("")) {
					JOptionPane.showMessageDialog(new UI().getFrame(), "Error: Input field cannot be empty!");
				} else {
					searchBook(ISBN, title);
				}
			} else if (e.getSource() == jbtMore) {

			} else if (e.getSource() == jbtLoad) {
				addBook("0131450913", "HTML How to Program");
				addBook("0131857576", "C++ How to Program");
				addBook("0132222205", "Java How to Program");
			} else if (e.getSource() == jbtDisplay) {
				Book bk;
				clearTable();
				for (int i = 0; i < bookLinkedList.size(); i++) {
					bk = bookLinkedList.get(i);
					tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
				}
			} else if (e.getSource() == jbtDisplayByISBN) {
				Book bk;
				clearTable();
				ArrayList Order = getSortOrder(1);
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
			} else if (e.getSource() == jbtDisplayByTitle) {
				Book bk;
				clearTable();
				ArrayList Order = getSortOrder(2);
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
			} else if (e.getSource() == jbtExit) {
				System.exit(0);
			}
		}
	}

	public void clearTextField() {
		inputISBN.setText("");
		inputTitle.setText("");
	}

	public boolean addBook(String ISBN, String title) {

		if (locateISBN(ISBN)!=-1) {
			JOptionPane.showMessageDialog(new UI().getFrame(),
					"Error: book " + ISBN + " exists in the current database");
			return false;
		} else {
			Book bk = new Book(ISBN, title);
			bookLinkedList.add(bk);
			tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			clearTextField();
			ISBN_ASC = false;
			Title_ASC = false;
			TextAreaLayer.refreshTime();
			return true;
		}
	}

	public boolean removeBook(String ISBN, String title) {	//awaits correction, if display no sort by node GG

		int index = locateISBN(ISBN);
		if (index!=-1) {
			for (int i=0;i<tModel.getRowCount();i++) {
				if (ISBN.equals(tModel.getValueAt(i, 0).toString())) {
					tModel.removeRow(i);
					break;
				}
			}
			bookLinkedList.remove(index);
			ISBN_ASC = false;
			Title_ASC = false;
			TextAreaLayer.refreshTime();
			return true;
		} else {
			JOptionPane.showMessageDialog(new UI().getFrame(),
					"Error: book " + ISBN + " does not exists in the current database");
			return false;
		}
	}

	public int editBook(String ISBN) {

		String title = "";
		int index = locateISBN(ISBN);

		if (index!=-1) {
			title = bookLinkedList.get(index).getTitle();
			inputTitle.setText(title);
			editMode();
		} else {
			JOptionPane.showMessageDialog(new UI().getFrame(),
					"Error: book " + ISBN + " does not exists in the current database");
		}
		return index;
	}

	public boolean saveBook(int index, String newISBN, String newTitle) {
		if (newISBN.equals("") || newTitle.equals("")) {
			JOptionPane.showMessageDialog(new UI().getFrame(), "Error: Input field cannot be empty!");
			return false;
		} else if (locateISBN(newISBN)!=-1) {
			JOptionPane.showMessageDialog(new UI().getFrame(),
					"Error: book " + newISBN + " exists in the current database");
			return false;
		} else {
			bookLinkedList.get(index).setISBN(newISBN);
			bookLinkedList.get(index).setTitle(newTitle);
			tModel.setValueAt(newISBN, index, 0);
			tModel.setValueAt(newTitle, index, 1);
			viewMode();
			ISBN_ASC = false;
			Title_ASC = false;
			TextAreaLayer.refreshTime();
			return true;
		}
	}

	private void searchBook(String ISBN, String Title) {
		clearTable();
		clearTextField();
		for (Book bk : bookLinkedList) {
			if ((!ISBN.equals(""))&&(bk.getISBN().contains(ISBN))) {
				tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			} else if ((!Title.equals(""))&&(bk.getTitle().contains(Title))) {
				tModel.addRow(new String[] { bk.getISBN(), bk.getTitle(), bk.getAvailable() });
			}
		}
	}

	private void editMode() {
		jbtSave.setEnabled(true);

		jbtAdd.setEnabled(false);
		jbtEdit.setEnabled(false);
		jbtDelete.setEnabled(false);
		jbtSearch.setEnabled(false);
		jbtMore.setEnabled(false);
		jbtLoad.setEnabled(false);
		jbtDisplay.setEnabled(false);
		jbtDisplayByISBN.setEnabled(false);
		jbtDisplayByTitle.setEnabled(false);
		jbtExit.setEnabled(false);

	}

	private void viewMode() {
		jbtSave.setEnabled(false);

		jbtAdd.setEnabled(true);
		jbtEdit.setEnabled(true);
		jbtDelete.setEnabled(true);
		jbtSearch.setEnabled(true);
		jbtMore.setEnabled(true);
		jbtLoad.setEnabled(true);
		jbtDisplay.setEnabled(true);
		jbtDisplayByISBN.setEnabled(true);
		jbtDisplayByTitle.setEnabled(true);
		jbtExit.setEnabled(true);
	}

	public void clearTable() {
		System.out.println(tModel.getRowCount());
		while (tModel.getRowCount() > 0) {
			tModel.removeRow(0);
		}
	}

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

	public int locateISBN(String ISBN) {
		for (int i = 0; i < bookLinkedList.size(); i++) {
			if (bookLinkedList.get(i).getISBN().equals(ISBN)) {
				return i;
			}
		}
		return -1;
	}
}
