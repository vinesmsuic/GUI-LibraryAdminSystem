/**
 * @author: Ku Wing Fung 18075712d
 * @author: Wong Tsz Hin 18050573d
 */

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

	//Default constructor
	public MyTableModel() {

	}
	
	//Constructor to store column header
	public MyTableModel(String[] header) {
		for (String s : header) {
			addColumn(s);
		}
	}

	//Set the cell to uneditable, since all cell in this program should not be editable
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	//Function to get the number of column
	@Override
	public int getColumnCount() {
		return super.getColumnCount();
	}
	
	//Function to get the number of row
	@Override
	public int getRowCount() {
		return super.getRowCount();
	}
	
	//Function to get the name of a column
	@Override
	public String getColumnName(int column) {
		return super.getColumnName(column);
	}
	
	//Function to get the value of a cell
	@Override
	public Object getValueAt(int row, int column) {
		return super.getValueAt(row, column);
	}
	
	//Function to set the value of a cell
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
	}
	
	//Function to add a row to the table
	@Override
	public void addRow(Object[] rowData) {
		super.addRow(rowData);
	}
	
	//Function to remove a row to the table
	@Override
	public void removeRow(int row) {
		super.removeRow(row);
	}
	
}
