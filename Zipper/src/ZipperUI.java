import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

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
