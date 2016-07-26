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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
	
	private JLabel lblAccountValue;
	
	private HashMap<String, User> accounts;
	private User activeUser;
	Accounts activeType;
	
	final static String ATM_MENU = "ATM Menu";
	final static String ACCOUNT_MENU = "Account Menu";
	final static String SIGN_IN_MENU = "Sign In Menu";
	final static String NEW_ACCOUNT_MENU = "New Account Menu";
	private JTextField txtNewPIN;
	
	private JButton btnNewAccount;
	
	private JPanel pnlNewAccount;
	
	private JTextField txtNewAccountSum;
	
	private JComboBox<Accounts> comboAccountTypes;

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
		
		JButton btnCreateAccount = new JButton("Create Account");
		pnlSignIn.add(btnCreateAccount);
		btnCreateAccount.addActionListener(new UserCreationListener());
		
		txtNewPIN = new JTextField();
		sl_pnlSignIn.putConstraint(SpringLayout.NORTH, txtNewPIN, 33, SpringLayout.SOUTH, txtEnterPin);
		txtNewPIN.setText("New PIN");
		sl_pnlSignIn.putConstraint(SpringLayout.NORTH, btnCreateAccount, -1, SpringLayout.NORTH, txtNewPIN);
		sl_pnlSignIn.putConstraint(SpringLayout.WEST, btnCreateAccount, 6, SpringLayout.EAST, txtNewPIN);
		sl_pnlSignIn.putConstraint(SpringLayout.WEST, txtNewPIN, 0, SpringLayout.WEST, txtEnterPin);
		pnlSignIn.add(txtNewPIN);
		txtNewPIN.setColumns(10);
		
		pnlATMMenu = new JPanel();
		cardContainerPanel.add(pnlATMMenu, ATM_MENU);
		pnlATMMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnViewSavings = new JButton("Savings");
		pnlATMMenu.add(btnViewSavings);
		btnViewSavings.addActionListener(new MenuSelectListener());
		
		JButton btnViewChecking = new JButton("Checking");
		pnlATMMenu.add(btnViewChecking);
		btnViewChecking.addActionListener(new MenuSelectListener());
		
		JButton btnViewBusiness = new JButton("Business");
		pnlATMMenu.add(btnViewBusiness);
		
		btnNewAccount = new JButton("New Account");
		pnlATMMenu.add(btnNewAccount);
		btnViewBusiness.addActionListener(new MenuSelectListener());
		
		pnlAccount = new JPanel();
		cardContainerPanel.add(pnlAccount, ACCOUNT_MENU);
		SpringLayout sl_pnlAccount = new SpringLayout();
		pnlAccount.setLayout(sl_pnlAccount);
		
		lblAccountValue = new JLabel("New label");
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
		
		JButton btnBack = new JButton("Back");
		sl_pnlAccount.putConstraint(SpringLayout.NORTH, btnBack, 6, SpringLayout.SOUTH, btnMakeWithdrawal);
		sl_pnlAccount.putConstraint(SpringLayout.WEST, btnBack, 0, SpringLayout.WEST, btnMakeDeposit);
		pnlAccount.add(btnBack);
		btnBack.addActionListener(new ReturnButtonListener());
		
		pnlNewAccount = new JPanel();
		cardContainerPanel.add(pnlNewAccount, NEW_ACCOUNT_MENU);
		SpringLayout sl_pnlNewAccount = new SpringLayout();
		pnlNewAccount.setLayout(sl_pnlNewAccount);
		
		txtNewAccountSum = new JTextField();
		txtNewAccountSum.setText("Initial Sum");
		sl_pnlNewAccount.putConstraint(SpringLayout.NORTH, txtNewAccountSum, 10, SpringLayout.NORTH, pnlNewAccount);
		sl_pnlNewAccount.putConstraint(SpringLayout.WEST, txtNewAccountSum, 10, SpringLayout.WEST, pnlNewAccount);
		pnlNewAccount.add(txtNewAccountSum);
		txtNewAccountSum.setColumns(10);
		
		comboAccountTypes = new JComboBox<Accounts>();
		comboAccountTypes.setModel(new DefaultComboBoxModel<Accounts>(Accounts.values()));
		sl_pnlNewAccount.putConstraint(SpringLayout.NORTH, comboAccountTypes, 6, SpringLayout.SOUTH, txtNewAccountSum);
		sl_pnlNewAccount.putConstraint(SpringLayout.WEST, comboAccountTypes, 0, SpringLayout.WEST, txtNewAccountSum);
		pnlNewAccount.add(comboAccountTypes);
		
		JButton btnGenerateAcct = new JButton("Create New Account");
		sl_pnlNewAccount.putConstraint(SpringLayout.NORTH, btnGenerateAcct, 6, SpringLayout.SOUTH, comboAccountTypes);
		sl_pnlNewAccount.putConstraint(SpringLayout.WEST, btnGenerateAcct, 0, SpringLayout.WEST, txtNewAccountSum);
		pnlNewAccount.add(btnGenerateAcct);
		btnGenerateAcct.addActionListener(new AccountAdditionListener());
		
		btnSubmitPIN.addActionListener(new PinListener());
		
	}
	
	//TODO hook in all these listeners, test behaviours
	
	
	class ReturnButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout) cardContainerPanel.getLayout();
			cl.show(cardContainerPanel, ATM_MENU);
		}
		
	}
	
	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class PinListener implements ActionListener {

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
	class MenuSelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			boolean accountActive = false;
			
			switch(e.getActionCommand()) {
			case "Checking": if(activeUser.hasAccount(Accounts.CHECKING)) {
				activeType = Accounts.CHECKING;
				accountActive = true;
			}
				break;
			case "Savings": if(activeUser.hasAccount(Accounts.SAVINGS)){
				activeType = Accounts.SAVINGS;
				accountActive = true;
			}
				break;
			case "Business": if(activeUser.hasAccount(Accounts.BUSINESS)){
				activeType = Accounts.BUSINESS;
				accountActive = true;
			}
				break;
			}
			
			if (accountActive) {
				
				CardLayout cl = (CardLayout) cardContainerPanel.getLayout();
				cl.show(cardContainerPanel, ACCOUNT_MENU);
				lblAccountValue.setText(Integer.toString(activeUser.accessAccount(activeType).getAmount()));
			}
			else {
				
				//TODO Set up option to create new accounts
				
			}	
			
		}
	
	}
	
	//add new account to this user
	class AccountAdditionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			CardLayout cl = (CardLayout) cardContainerPanel.getLayout();
			cl.show(cardContainerPanel, NEW_ACCOUNT_MENU);
			
			try {
				int initAmt = Integer.parseInt(txtNewAccountSum.getText());
				Accounts type = (Accounts) comboAccountTypes.getSelectedItem();
				
				activeUser.addAccount(type, initAmt);
				
			} catch(NumberFormatException nfe) {
				System.out.println("Invalid value.");
			} catch(Exception e) {
				System.out.println("Something went wrong.");
			}
			
		}
		
	}
	
	//creates new user from pin login screen
	class UserCreationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				
				String newUserPIN = txtNewPIN.getText();
				
				User newUser = new User(newUserPIN);
				
				accounts.put(newUserPIN, newUser);
				
			} catch(NumberFormatException nfe) {
				System.out.println("Invalid value.");
			}
		}
		
	}
	
	//TODO for both of these change error to in-UI label that appears when error caught
	class DepositListener implements ActionListener {

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
	
	class WithdrawalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				int withdrawAmt = Integer.parseInt(txtWithdraw.getText());
				
				activeUser.accessAccount(activeType).withdraw(withdrawAmt);
				
			} catch(NumberFormatException nfe) {
				System.out.println("Invalid.");
				return;
			}
			
		}
		
	}
}
