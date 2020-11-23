import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

	
	public MyTableModel() {

	}
	
	public MyTableModel(String[] header) {
		for (String s : header) {
			addColumn(s);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return super.getColumnCount();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return super.getRowCount();
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return super.getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return super.getValueAt(row, column);
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}
	
	@Override
	public void addRow(Object[] rowData) {
		// TODO Auto-generated method stub
		super.addRow(rowData);
	}
	
	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		super.removeRow(row);
	}
	
}
