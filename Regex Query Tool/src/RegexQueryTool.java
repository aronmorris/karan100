import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class RegexQueryTool {

	private JFrame frame;
	private JTextField regexField;
	private JTextPane sourcePane;
	private JButton submitButton;
	private JTextArea matchArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegexQueryTool window = new RegexQueryTool();
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
	public RegexQueryTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		sourcePane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, sourcePane, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, sourcePane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, sourcePane, 251, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, sourcePane, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(sourcePane);
		
		regexField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, regexField, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, regexField, 6, SpringLayout.EAST, sourcePane);
		springLayout.putConstraint(SpringLayout.EAST, regexField, 124, SpringLayout.EAST, sourcePane);
		frame.getContentPane().add(regexField);
		regexField.setColumns(10);
		
		submitButton = new JButton("Enter");
		springLayout.putConstraint(SpringLayout.NORTH, submitButton, -1, SpringLayout.NORTH, regexField);
		springLayout.putConstraint(SpringLayout.WEST, submitButton, 6, SpringLayout.EAST, regexField);
		frame.getContentPane().add(submitButton);
		submitButton.addActionListener(new SubmissionListener());
		
		matchArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, matchArea, 6, SpringLayout.SOUTH, regexField);
		springLayout.putConstraint(SpringLayout.WEST, matchArea, 6, SpringLayout.EAST, sourcePane);
		springLayout.putConstraint(SpringLayout.SOUTH, matchArea, 0, SpringLayout.SOUTH, sourcePane);
		springLayout.putConstraint(SpringLayout.EAST, matchArea, 214, SpringLayout.EAST, sourcePane);
		frame.getContentPane().add(matchArea);
		matchArea.setEditable(false);
	}
	
	class SubmissionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String regex = regexField.getText();
			
			try {
				Pattern pReg = Pattern.compile(regex);
			} catch (PatternSyntaxException pse) {
				matchArea.setText("Invalid Regex");
				return;
			}
			
			ArrayList<String> matches = RegexQuery.regexQuery(sourcePane.getText(), regex);
			
			StringBuilder sb = new StringBuilder();
			
			for (String str : matches) {
				sb.append(str + "\n");
			}
			
			matchArea.setText(sb.toString());
			
		}
		
	}
}
