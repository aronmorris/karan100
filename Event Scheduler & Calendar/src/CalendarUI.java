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
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CalendarUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtDBUser;
	private JTextField txtDBPW;

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
		frame.setBounds(100, 100, 803, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtAreaEventDescription = new JTextArea();
		txtAreaEventDescription.setToolTipText("Enter event information here for a selected date from the calendar.");
		txtAreaEventDescription.setText("Enter event info for your chosen date.");
		txtAreaEventDescription.setBounds(324, 10, 304, 190);
		frame.getContentPane().add(txtAreaEventDescription);
		
		textField = new JTextField();
		textField.setBounds(638, 45, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JTable tableEvents = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tableEvents.setShowGrid(false);
		
		CalendarPopulator calendarPopulator = new CalendarPopulator(tableEvents);
		calendarPopulator.setBounds(10, 10, 304, 190);
		frame.getContentPane().add(calendarPopulator);
		
		JScrollPane scrollPaneEvents = new JScrollPane();
		scrollPaneEvents.setBounds(10, 207, 618, 190);
		frame.getContentPane().add(scrollPaneEvents);
		
		DefaultTableModel dtModel = new DefaultTableModel();
		
		dtModel.addColumn("Date");
		dtModel.addColumn("Time");
		dtModel.addColumn("Description");
		
		String[] test = {"YYYY-MM-DD", "HH:MM:SS", "ipsum lorem"};
		
		tableEvents.setModel(dtModel);
		tableEvents.getColumnModel().getColumn(2).setPreferredWidth(300); //set the width of the description column to the proper size
		
		dtModel.addRow(test);
		
		tableEvents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneEvents.setViewportView(tableEvents);
		
		JButton btnAddDescription = new JButton("Add Desc.");
		btnAddDescription.setBounds(638, 11, 89, 23);
		frame.getContentPane().add(btnAddDescription);
		
		JLabel lblInvalidTime = new JLabel("Use HH:MM:ss");
		lblInvalidTime.setForeground(Color.RED);
		lblInvalidTime.setBounds(638, 70, 86, 14);
		frame.getContentPane().add(lblInvalidTime);
		
		JButton btnAddTime = new JButton("Add Time");
		btnAddTime.setBounds(638, 95, 89, 23);
		frame.getContentPane().add(btnAddTime);
		
		JButton btnFinalized = new JButton("Add Event");
		btnFinalized.setBounds(638, 374, 89, 23);
		frame.getContentPane().add(btnFinalized);
		
		txtDBUser = new JTextField();
		txtDBUser.setBounds(638, 180, 86, 20);
		frame.getContentPane().add(txtDBUser);
		txtDBUser.setColumns(10);
		
		txtDBPW = new JTextField();
		txtDBPW.setBounds(638, 205, 86, 20);
		frame.getContentPane().add(txtDBPW);
		txtDBPW.setColumns(10);
		
		JButton btnDBConnect = new JButton("Connect");
		btnDBConnect.setBounds(638, 236, 89, 23);
		frame.getContentPane().add(btnDBConnect);
		ButtonListener descListener = new ButtonListener(txtAreaEventDescription);
		
		btnDBConnect.addActionListener(new CredentialsListener(txtDBUser, txtDBPW)); //logs into the database server
		
		ButtonListener timeListener = new ButtonListener(textField);
		
		btnAddDescription.addActionListener(descListener);
		
		btnAddTime.addActionListener(timeListener);
		
		btnFinalized.addActionListener(new EventAssemblyListener(calendarPopulator, descListener, timeListener));
		

		//TODO hook in listeners
		
		
		
	}
}
