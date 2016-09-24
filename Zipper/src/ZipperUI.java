import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/*
 * UI is used to find and select one or multiple files without relying on
 * CLI, easier for general operation of the program
 */
public class ZipperUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZipperUI window = new ZipperUI();
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
	public ZipperUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, frame.getContentPane());
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 164, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, panel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -374, SpringLayout.EAST, frame.getContentPane());
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(panel_1);
		
		int widthPos = frame.WIDTH;
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem menuFileSelectButton = new JMenuItem("Select File(s)");
		menuBar.add(menuFileSelectButton);
		
		JMenuItem menuFileZipButton = new JMenuItem("Zip File(s)");
		menuFileZipButton.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(menuFileZipButton);
		
		JMenuItem MenuFileUnzipButton = new JMenuItem("Unzip File");
		MenuFileUnzipButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(MenuFileUnzipButton);
		
		JMenuItem menuExitButton = new JMenuItem("Exit");
		menuExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuExitButton);
	}
}
