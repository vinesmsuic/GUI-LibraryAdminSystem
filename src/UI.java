import javax.swing.*;
import java.awt.*;

public class UI extends JFrame{
	
	JPanel frameHolder = new JPanel();
	
	//Adding interactive layer to the UI of library admin system
	public UI() {
		frameHolder.setLayout(new BorderLayout(5,5));
		frameHolder.add(new InteractiveLayer(this), BorderLayout.CENTER);
		add(frameHolder);
	}

	public static void main(String[] args) {
		UI frame = new UI();
		frame.setTitle("Library Admin System");
		frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);	//Center the frame 
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		//So that the action of closing frame can be reversed
        frame.setVisible(true);
	}
	
}
