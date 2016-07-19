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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JLabel;


public class atmWindow {

	private JFrame frame;
	private JTextField txtEnterPin;
	private JTextField txtDeposit;
	private JTextField txtWithdraw;
	
	//TODO declare these in initialize
	private HashMap<String, User> accounts;
	private User activeUser;

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
		
		JPanel pnlAccount = new JPanel();
		frame.getContentPane().add(pnlAccount, "name_160311075315663");
		SpringLayout sl_pnlAccount = new SpringLayout();
		pnlAccount.setLayout(sl_pnlAccount);
		
		JLabel lblAccountValue = new JLabel("New label");
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, lblAccountValue, 10, SpringLayout.NORTH, pnlAccount);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, lblAccountValue, 10, SpringLayout.WEST, pnlAccount);
		pnlAccount.add(lblAccountValue);
		
		JButton btnMakeDeposit = new JButton("Deposit");
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, btnMakeDeposit, 31, SpringLayout.NORTH, pnlAccount);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, btnMakeDeposit, 97, SpringLayout.WEST, pnlAccount);
		pnlAccount.add(btnMakeDeposit);
		
		txtDeposit = new JTextField();
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, txtDeposit, 1, SpringLayout.NORTH, btnMakeDeposit);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, txtDeposit, 0, SpringLayout.WEST, lblAccountValue);
		pnlAccount.add(txtDeposit);
		txtDeposit.setColumns(10);
		
		txtWithdraw = new JTextField();
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, txtWithdraw, 6, SpringLayout.SOUTH, btnMakeDeposit);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, txtWithdraw, 0, SpringLayout.WEST, lblAccountValue);
		pnlAccount.add(txtWithdraw);
		txtWithdraw.setColumns(10);
		
		JButton btnMakeWithdrawal = new JButton("Withdraw");
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, btnMakeWithdrawal, 6, SpringLayout.SOUTH, btnMakeDeposit);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, btnMakeWithdrawal, 0, SpringLayout.WEST, btnMakeDeposit);
		pnlAccount.add(btnMakeWithdrawal);
	}
	
	class pinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (accounts.containsKey(txtEnterPin.getText())) {
				activeUser = accounts.get(txtEnterPin.getText());
			}
			
		}
		
	}
}
