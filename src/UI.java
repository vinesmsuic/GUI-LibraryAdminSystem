import javax.swing.*;
import java.awt.*;

public class UI extends JFrame{
	
	JPanel frameHolder = new JPanel();
	
	public UI() {

		frameHolder.setLayout(new BorderLayout(5,5));
//		frameHolder.add(new TextAreaLayer(), BorderLayout.NORTH);
//		frameHolder.add(new TableLayer(), BorderLayout.CENTER);
//		frameHolder.add(new InteractiveLayer(), BorderLayout.SOUTH);
		
		frameHolder.add(new TextAreaLayer(), BorderLayout.NORTH);
		frameHolder.add(new InteractiveLayer(), BorderLayout.CENTER);
		
		add(frameHolder);
	}

	public static void main(String[] args) {

		UI frame = new UI();
		frame.setTitle("Library Admin System");
		frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the frame 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	public JPanel getFrame() {
		return frameHolder;
	}
	
}
