import javax.swing.*;
import java.awt.*;

public class InteractiveLayer extends JPanel {
	
	JPanel inputPanel = new JPanel();
	JPanel selectPanel = new JPanel();
	JPanel lastPanel = new JPanel();
	
	public InteractiveLayer() {
		
		JLabel jlISBN = new JLabel("ISBN:");
		JTextField inputISBN = new JTextField();
		JLabel jlTitle = new JLabel("Title:");
		JTextField inputTitle = new JTextField();
		
		inputISBN.setPreferredSize(new Dimension(100, 20));
		inputTitle.setPreferredSize(new Dimension(100, 20));
		
		JButton jbtAdd = new JButton("Add");
		JButton jbtEdit = new JButton("Edit");
		JButton jbtSave = new JButton("Save");
		JButton jbtDelete = new JButton("Delete");
		JButton jbtSearch = new JButton("Search");
		JButton jbtMore = new JButton("More>>");
		
		JButton jbtLoad = new JButton("Load Test Data");
		JButton jbtDisplay = new JButton("Display All");
		JButton jbtDisplayByISBN = new JButton("Display All by ISBN");
		JButton jbtDisplayByTitle = new JButton("Display All by Title");
		JButton jbtExit = new JButton("Exit");
		
		inputPanel.add(jlISBN);
		inputPanel.add(inputISBN);
		inputPanel.add(jlTitle);
		inputPanel.add(inputTitle);
		
		selectPanel.add(jbtAdd);
		selectPanel.add(jbtEdit);
		selectPanel.add(jbtSave);
		selectPanel.add(jbtDelete);
		selectPanel.add(jbtSearch);
		selectPanel.add(jbtMore);
		
		lastPanel.add(jbtLoad);
		lastPanel.add(jbtDisplay);
		lastPanel.add(jbtDisplayByISBN);
		lastPanel.add(jbtDisplayByTitle);
		lastPanel.add(jbtExit);
		
		setLayout(new GridLayout(3, 1, 5, 5));
		add(inputPanel);
		add(selectPanel);
		add(lastPanel);
	}

}
