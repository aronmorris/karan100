import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;


public class atmWindow {

	private JFrame frame;
	private JTextField txtEnterPin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					atmWindow window = new atmWindow();
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
	public atmWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel pnlSignIn = new JPanel();
		frame.getContentPane().add(pnlSignIn, "name_159791925385046");
		SpringLayout sl_pnlSignIn = new SpringLayout();
		pnlSignIn.setLayout(sl_pnlSignIn);
		
		txtEnterPin = new JTextField();
		sl_pnlSignIn.putConstraint(SpringLayout.NORTH, txtEnterPin, 10, SpringLayout.NORTH, pnlSignIn);
		sl_pnlSignIn.putConstraint(SpringLayout.WEST, txtEnterPin, 10, SpringLayout.WEST, pnlSignIn);
		sl_pnlSignIn.putConstraint(SpringLayout.EAST, txtEnterPin, 165, SpringLayout.WEST, pnlSignIn);
		txtEnterPin.setText("Enter PIN");
		pnlSignIn.add(txtEnterPin);
		txtEnterPin.setColumns(10);
		
		JButton btnSubmitPIN = new JButton("Submit");
		sl_pnlSignIn.putConstraint(SpringLayout.WEST, btnSubmitPIN, 6, SpringLayout.EAST, txtEnterPin);
		sl_pnlSignIn.putConstraint(SpringLayout.SOUTH, btnSubmitPIN, 0, SpringLayout.SOUTH, txtEnterPin);
		pnlSignIn.add(btnSubmitPIN);
		
		JPanel pnlATMMenu = new JPanel();
		frame.getContentPane().add(pnlATMMenu, "name_160161626461799");
		pnlATMMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnViewSavings = new JButton("Savings");
		pnlATMMenu.add(btnViewSavings);
		
		JButton btnViewChecking = new JButton("Checking");
		pnlATMMenu.add(btnViewChecking);
		
		JButton btnViewBusiness = new JButton("Business");
		pnlATMMenu.add(btnViewBusiness);
		
		JPanel pnlChecking = new JPanel();
		frame.getContentPane().add(pnlChecking, "name_160311075315663");
		SpringLayout springLayout = new SpringLayout();
		pnlChecking.setLayout(springLayout);
		
		JLabel lblAccountValue = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, lblAccountValue, 10, SpringLayout.NORTH, pnlChecking);
		springLayout.putConstraint(SpringLayout.WEST, lblAccountValue, 10, SpringLayout.WEST, pnlChecking);
		pnlChecking.add(lblAccountValue);
		
		JButton btnMakeDeposit = new JButton("Deposit");
		springLayout.putConstraint(SpringLayout.NORTH, btnMakeDeposit, 31, SpringLayout.NORTH, pnlChecking);
		springLayout.putConstraint(SpringLayout.WEST, btnMakeDeposit, 97, SpringLayout.WEST, pnlChecking);
		pnlChecking.add(btnMakeDeposit);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 1, SpringLayout.NORTH, btnMakeDeposit);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblAccountValue);
		pnlChecking.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, btnMakeDeposit);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, lblAccountValue);
		pnlChecking.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel pnlSavings = new JPanel();
		frame.getContentPane().add(pnlSavings, "name_160311107646049");
		pnlSavings.setLayout(new SpringLayout());
		
		JPanel pnlBusiness = new JPanel();
		frame.getContentPane().add(pnlBusiness, "name_160311130721795");
		pnlBusiness.setLayout(new SpringLayout());
	}
}
