import javax.swing.*;
import java.awt.*;

public class TableLayer extends JPanel{
	
	String[] tableHeader = {"ISBN", "Title", "Available"};
	String[][] placeHolder = {{"Fuck", "Fuck", "fuck"},{"Fuck", "Fuck", "fuck"} };
	
	JTable jtb = new JTable(placeHolder , tableHeader);
	JScrollPane jspane = new JScrollPane(jtb);

	
	public TableLayer() {

		setLayout(new GridLayout(0,1));
		add(jspane);
	}
}
