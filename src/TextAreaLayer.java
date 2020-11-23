import javax.swing.*;
import java.awt.*;

public class TextAreaLayer extends JPanel {
	
	JTextArea jTextArea;
	

	public TextAreaLayer() {
		String names = "Student Name and ID: Ku Wing Fung (18075712d) \n"
				+ "Student Name and ID: Wong Tsz Hin (18050573d) \n";
		
		jTextArea = new JTextArea(names);
		
		setLayout(new GridLayout(0,1));
		add(jTextArea);
	}
}
