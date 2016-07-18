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


public class atmWindow {

	private JFrame frame;
	private JTextField txtEnterPin;

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
		pnlATMMenu.setLayout(new SpringLayout());
		
		JPanel pnlChecking = new JPanel();
		frame.getContentPane().add(pnlChecking, "name_160311075315663");
		pnlChecking.setLayout(new SpringLayout());
		
		JPanel pnlSavings = new JPanel();
		frame.getContentPane().add(pnlSavings, "name_160311107646049");
		pnlSavings.setLayout(new SpringLayout());
		
		JPanel pnlBusiness = new JPanel();
		frame.getContentPane().add(pnlBusiness, "name_160311130721795");
		pnlBusiness.setLayout(new SpringLayout());
	}
}
