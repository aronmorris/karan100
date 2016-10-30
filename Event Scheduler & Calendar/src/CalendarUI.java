import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class CalendarUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarUI window = new CalendarUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalendarUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelCalendar = new JPanel();
		panelCalendar.setBounds(10, 11, 650, 539);
		frame.getContentPane().add(panelCalendar);
		
		JButton btnNextMonth = new JButton("Next");
		btnNextMonth.setBounds(670, 11, 89, 23);
		frame.getContentPane().add(btnNextMonth);
		
		JButton btnPreviousMonth = new JButton("Previous");
		btnPreviousMonth.setBounds(670, 45, 89, 23);
		frame.getContentPane().add(btnPreviousMonth);
		
		JTextArea txtAreaEventDescription = new JTextArea();
		txtAreaEventDescription.setText("Enter event information");
		txtAreaEventDescription.setBounds(670, 79, 304, 200);
		frame.getContentPane().add(txtAreaEventDescription);
	}
}
