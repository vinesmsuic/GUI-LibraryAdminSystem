import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class TextAreaLayer extends JPanel {
	
	private static JTextArea Time;
	private static SimpleDateFormat formatter;

	public TextAreaLayer() {
		String names = "Student Name and ID: Ku Wing Fung (18075712d) \n"
				+ "Student Name and ID: Wong Tsz Hin (18050573d)";
		formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		Time = new JTextArea(formatter.format(new Date(System.currentTimeMillis())));
		setLayout(new GridLayout(0,1));
		add(new JTextArea(names));
		add(Time);
	}

	public static void refreshTime() {
		Time.setText(formatter.format(new Date(System.currentTimeMillis())));
	}
}
