import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;


public class SnippetUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnippetUI window = new SnippetUI();
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
	public SnippetUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 31);
		frame.getContentPane().add(menuBar);
		
		JMenuItem menuExitButton = new JMenuItem("Exit");
		menuExitButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(menuExitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 400, 358);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		model.addElement("Hello world");
		
		JList<String> listOfSnippets = new JList<String>(model);
		scrollPane.setViewportView(listOfSnippets);
		
		MouseListener listListener = new SnippetReader(listOfSnippets);
		
		listOfSnippets.addMouseListener(listListener);
		
		
		JTextArea txtAreaSnippetSubmitter = new JTextArea();
		txtAreaSnippetSubmitter.setBounds(420, 42, 354, 200);
		frame.getContentPane().add(txtAreaSnippetSubmitter);
		
		JButton btnSubmitSnippet = new JButton("Submit");
		btnSubmitSnippet.setBounds(420, 253, 89, 23);
		frame.getContentPane().add(btnSubmitSnippet);
	}
}
