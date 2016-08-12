import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.JButton;


public class FamilyTreeViewer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FamilyTreeViewer window = new FamilyTreeViewer();
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
	public FamilyTreeViewer() {
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
		
		JPanel treeDisplayPanel = new JPanel();
		treeDisplayPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(treeDisplayPanel, "name_689299405180623");
		treeDisplayPanel.setLayout(null);
		
		JTree tree = new JTree();
		tree.setBounds(0, 0, 328, 261);
		treeDisplayPanel.add(tree);
		
		JButton btnBack = new JButton("New button");
		btnBack.setBounds(335, 11, 89, 23);
		treeDisplayPanel.add(btnBack);
		
		JPanel addMembersPanel = new JPanel();
		frame.getContentPane().add(addMembersPanel, "name_689334841623690");
		addMembersPanel.setLayout(null);
		
		JPanel menuPanel = new JPanel();
		frame.getContentPane().add(menuPanel, "name_689738373664520");
		menuPanel.setLayout(null);
	}
}
