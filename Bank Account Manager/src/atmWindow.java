import java.awt.EventQueue;

import javax.swing.JFrame;

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

/*
 * The plan here is to reuse the text fields and buttons for each class, only showing what is actually necessary for each account.
 * e.g. the active account type is the parameter that determines what info will be pulled out
 *  evt.getActionCommand()?
 */


public class atmWindow {

	private JFrame frame;
	private JTextField txtEnterPin;
	private JTextField txtDeposit;
	private JTextField txtWithdraw;
	
	private JPanel pnlSignIn;
	private JPanel pnlATMMenu;
	private JPanel pnlAccount;
	private JPanel cardContainerPanel;
	
	private HashMap<String, User> accounts;
	private User activeUser;
	Accounts activeType;
	
	final static String ATM_MENU = "ATM Menu";
	final static String ACCOUNT_MENU = "Account Menu";
	final static String SIGN_IN_MENU = "Sign In Menu";

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
		
		accounts = new HashMap<String, User>();
		
		User baron = new User("1111");
		
		baron.addAccount(Accounts.CHECKING, 200);
		
		accounts.put("1111", baron);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		cardContainerPanel = new JPanel();
		frame.getContentPane().add(cardContainerPanel, "name_341291192584994");
		cardContainerPanel.setLayout(new CardLayout(0, 0));
		
		pnlSignIn = new JPanel();
		cardContainerPanel.add(pnlSignIn, SIGN_IN_MENU);
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
		
		pnlATMMenu = new JPanel();
		cardContainerPanel.add(pnlATMMenu, ATM_MENU);
		pnlATMMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnViewSavings = new JButton("Savings");
		pnlATMMenu.add(btnViewSavings);
		btnViewSavings.addActionListener(new menuSelectListener());
		
		JButton btnViewChecking = new JButton("Checking");
		pnlATMMenu.add(btnViewChecking);
		btnViewChecking.addActionListener(new menuSelectListener());
		
		JButton btnViewBusiness = new JButton("Business");
		pnlATMMenu.add(btnViewBusiness);
		btnViewBusiness.addActionListener(new menuSelectListener());
		
		pnlAccount = new JPanel();
		cardContainerPanel.add(pnlAccount, ACCOUNT_MENU);
		SpringLayout sl_pnlAccount = new SpringLayout();
		pnlAccount.setLayout(sl_pnlAccount);
		
		JLabel lblAccountValue = new JLabel("New label");
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, lblAccountValue, 10, SpringLayout.NORTH, pnlAccount);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, lblAccountValue, 10, SpringLayout.WEST, pnlAccount);
		pnlAccount.add(lblAccountValue);
		
		JButton btnMakeDeposit = new JButton("Deposit");
		pnlAccount.add(btnMakeDeposit);
		
		txtDeposit = new JTextField();
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, btnMakeDeposit, -1, SpringLayout.NORTH, txtDeposit);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, btnMakeDeposit, 22, SpringLayout.EAST, txtDeposit);
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, txtDeposit, 8, SpringLayout.SOUTH, lblAccountValue);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, txtDeposit, 0, SpringLayout.WEST, lblAccountValue);
		pnlAccount.add(txtDeposit);
		txtDeposit.setColumns(10);
		
		txtWithdraw = new JTextField();
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, txtWithdraw, 8, SpringLayout.SOUTH, txtDeposit);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, txtWithdraw, 0, SpringLayout.WEST, lblAccountValue);
		pnlAccount.add(txtWithdraw);
		txtWithdraw.setColumns(10);
		
		JButton btnMakeWithdrawal = new JButton("Withdraw");
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, btnMakeWithdrawal, -1, SpringLayout.NORTH, txtWithdraw);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, btnMakeWithdrawal, 0, SpringLayout.WEST, btnMakeDeposit);
		pnlAccount.add(btnMakeWithdrawal);
		btnSubmitPIN.addActionListener(new pinListener());
		
	}
	
	//TODO hook in all these listeners, test behaviours
	
	class pinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (accounts.containsKey(txtEnterPin.getText())) {
				activeUser = accounts.get(txtEnterPin.getText());
				CardLayout cl = (CardLayout) cardContainerPanel.getLayout();
				cl.show(cardContainerPanel, ATM_MENU);
			}
			
		}
		
	}
	
	//defines the selective account based on the button pressed
	//3 options are the text on the buttons in the ATM Menu panel
	class menuSelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(e.getActionCommand()) {
			case "Checking": activeType = Accounts.CHECKING;
				break;
			case "Savings": activeType = Accounts.SAVINGS;
				break;
			case "Business": activeType = Accounts.BUSINESS;
				break;
			}
			
		}
		
	}
	
	class depositListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int depositAmt = Integer.parseInt(txtDeposit.getText());
				
				activeUser.accessAccount(activeType).deposit(depositAmt);
				
			} catch(NumberFormatException nfe) {
				System.out.println("Invalid.");
				return;
			}
			
		}
		
	}
	
	class withdrawalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
