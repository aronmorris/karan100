import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
		frame.setBounds(100, 100, 803, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtAreaEventDescription = new JTextArea();
		txtAreaEventDescription.setText("Enter event information");
		txtAreaEventDescription.setBounds(324, 10, 304, 190);
		frame.getContentPane().add(txtAreaEventDescription);
		
		CalendarPopulator calendarPopulator = new CalendarPopulator();
		calendarPopulator.setBounds(10, 10, 304, 190);
		frame.getContentPane().add(calendarPopulator);
		
		JScrollPane scrollPaneEvents = new JScrollPane();
		scrollPaneEvents.setBounds(10, 207, 618, 190);
		frame.getContentPane().add(scrollPaneEvents);
		
		JList listEvents = new JList();
		scrollPaneEvents.setViewportView(listEvents);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(638, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//AUTOGENERATED ABOVE BY DESIGN VIEW
		
		
		
	}
}
