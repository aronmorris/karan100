import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
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
import java.awt.Dimension;
import java.awt.Font;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JList;

/*
 * UI is used to find and select one or multiple files without relying on
 * CLI, easier for general operation of the program
 */
public class ZipperUI {
	
	private File[] filesSelected;
	
	private File[] unzippedFiles;
	
	private DefaultListModel<String> beforeListModel = new DefaultListModel<>();
	private DefaultListModel<String> afterListModel = new DefaultListModel<>();
	
	private String zipFileName, destinationFolderName = "D:/Programming/Java/JavaWorkspace/Projects/Zipper/resources/";
	
	//exit the system
	private class CloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0); 
			
		}
		
	}
	
	private class ZipListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			afterListModel.clear();
			
			FileZipper zipper = new FileZipper(destinationFolderName, filesSelected);
			
			File dest = zipper.zip();
			
			afterListModel.addElement(dest.getName() + ": " + dest.getAbsolutePath());
			
		}
		
	}
	
	private class UnZipListener implements ActionListener {

		final int BUFFER = 2048;
		//unzips the files selected in the filechooser if they're zip files
		//then writes them to a new file
		@Override
		public void actionPerformed(ActionEvent e) {
			
			afterListModel.clear();
			
			FileUnzipper unzipper = new FileUnzipper(filesSelected, destinationFolderName);
			
			for (File f : filesSelected) {
				
				File dest = unzipper.unzip();
				
				afterListModel.addElement(dest.getName() + ": " + dest.getAbsolutePath()); 
				
				System.out.println("Extracted: " + dest.getName() + " to : " + dest.getAbsolutePath());
			}
			
		}
	
		
	}

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
		
		JPanel fileResultDisplayPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, fileResultDisplayPanel, 30, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, fileResultDisplayPanel, -10, SpringLayout.EAST, frame.getContentPane());
		fileResultDisplayPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.SOUTH, fileResultDisplayPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(fileResultDisplayPanel);
		
		JPanel fileChooserPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, fileChooserPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, fileChooserPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, fileChooserPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, fileChooserPanel, -374, SpringLayout.EAST, frame.getContentPane());
		fileChooserPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(fileChooserPanel);
		
		JLabel labelOperationDirection = new JLabel("-->");
		springLayout.putConstraint(SpringLayout.WEST, labelOperationDirection, 40, SpringLayout.EAST, fileChooserPanel);
		springLayout.putConstraint(SpringLayout.WEST, fileResultDisplayPanel, 45, SpringLayout.EAST, labelOperationDirection);
		springLayout.putConstraint(SpringLayout.NORTH, labelOperationDirection, 120, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, fileResultDisplayPanel, 164, SpringLayout.EAST, fileChooserPanel);
		springLayout.putConstraint(SpringLayout.NORTH, fileChooserPanel, 0, SpringLayout.NORTH, fileResultDisplayPanel);
		springLayout.putConstraint(SpringLayout.WEST, fileChooserPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, fileChooserPanel, 0, SpringLayout.SOUTH, fileResultDisplayPanel);
		SpringLayout sl_fileResultDisplayPanel = new SpringLayout();
		fileResultDisplayPanel.setLayout(sl_fileResultDisplayPanel);
		
		JList<String> listFilesPostOperated = new JList<String>(afterListModel);
		fileResultDisplayPanel.add(listFilesPostOperated);
		springLayout.putConstraint(SpringLayout.EAST, fileChooserPanel, -374, SpringLayout.EAST, frame.getContentPane());
		SpringLayout sl_fileChooserPanel = new SpringLayout();
		fileChooserPanel.setLayout(sl_fileChooserPanel);
		
		JList<String> listFilesSelected = new JList<String>(beforeListModel);
		fileChooserPanel.add(listFilesSelected);
		labelOperationDirection.setFont(new Font("Tahoma", Font.PLAIN, 54));
		labelOperationDirection.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelOperationDirection);
		
		JLabel labelBefore = new JLabel("Before");
		springLayout.putConstraint(SpringLayout.WEST, labelBefore, 77, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, labelBefore, -6, SpringLayout.NORTH, fileChooserPanel);
		frame.getContentPane().add(labelBefore);
		
		JLabel labelAfter = new JLabel("After");
		springLayout.putConstraint(SpringLayout.NORTH, labelAfter, 0, SpringLayout.NORTH, labelBefore);
		springLayout.putConstraint(SpringLayout.EAST, labelAfter, -99, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(labelAfter);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{fileResultDisplayPanel, fileChooserPanel, labelOperationDirection}));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//this button opens the file chooser
		//files selected from it will be placed in the left panel to be listed and confirmed for zip/unzip operations
		//Where the final result will be available in the right panel
		JMenuItem menuFileSelectButton = new JMenuItem("Select File(s)");
		menuFileSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				beforeListModel.clear(); //remove all elements currently in the JList so it doesn't get cluttered by adding new ones
				
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setDialogTitle("Choose file");
				
				fileChooser.setVisible(true);
				
				fileChooser.setPreferredSize(new Dimension(200, 300));
				
				fileChooser.setMultiSelectionEnabled(true);
				
				int res = fileChooser.showOpenDialog(menuFileSelectButton);
				
				filesSelected = fileChooser.getSelectedFiles();
				
				if (res == JFileChooser.APPROVE_OPTION) {
					
					for (File f : filesSelected) { //populate the UI with the model

						zipFileName = f.getAbsolutePath();
						
						beforeListModel.addElement(f.getName());
					}
					
				} else if (res == JFileChooser.CANCEL_OPTION) {
				    System.out.println("Cancel was selected");
				}
				
			}
		});
		menuBar.add(menuFileSelectButton);
		
		JMenuItem menuFileZipButton = new JMenuItem("Zip File(s)");
		menuFileZipButton.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(menuFileZipButton);
		menuFileZipButton.addActionListener(new ZipListener()); //adds unzip utility 
		
		JMenuItem menuFileUnzipButton = new JMenuItem("Unzip File");
		menuFileUnzipButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuFileUnzipButton);
		menuFileUnzipButton.addActionListener(new UnZipListener()); //adds unzip utility to the button
		
		
		JMenuItem menuExitButton = new JMenuItem("Exit");
		menuExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuExitButton.addActionListener(new CloseListener()); //exit the program
		menuBar.add(menuExitButton); 
	
	}
}
