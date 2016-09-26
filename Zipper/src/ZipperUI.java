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
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.BoxLayout;

/*
 * UI is used to find and select one or multiple files without relying on
 * CLI, easier for general operation of the program
 */
public class ZipperUI {
	
	private File[] filesSelected;
	
	private ArrayList<File> unzippedFiles = new ArrayList<File>();
	
	//exit the system
	private class CloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0); 
			
		}
		
	}
	
	private class UnZipListener implements ActionListener {

		final int BUFFER = 2048;
		//unzips the files selected in the filechooser if they're zip files
		//then writes them to a new file
		@Override
		public void actionPerformed(ActionEvent e) {
			if (filesSelected != null) {
				for (File f : filesSelected) {
					if (!f.getName().substring(f.getName().length() - 4).equals(".zip")) { //skip this iteration of the loop if the file isn't a .zip file
						continue;
					}
					else {
						 try {
					         BufferedOutputStream dest = null;
					         
					         FileInputStream fis = new FileInputStream(f);
					         
					         ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
					         
					         ZipEntry entry;
					         
					         File decompressed;
					         
					         while((entry = zis.getNextEntry()) != null) {
					        	 
					            System.out.println("Extracting: " + entry);
					            int count;
					            
					            byte data[] = new byte[BUFFER];
					            // write the files to the disk
					            
					            decompressed = new File(f.getAbsolutePath()); //make new file in the same directory as the original zipped file
					            
					            FileOutputStream fos = new FileOutputStream(decompressed);
					            
					            dest = new BufferedOutputStream(fos, BUFFER);
					            
					            while ((count = zis.read(data, 0, BUFFER)) != -1) {
					               dest.write(data, 0, count);
					            }
					            
					            unzippedFiles.add(decompressed); //add to unzippedFiles list to display in the completed work panel
				
					            dest.flush();
					            dest.close();
					         }
					         zis.close();
					         
					      } catch(Exception except) {
					         except.printStackTrace();
					      }
					}
				}
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
		springLayout.putConstraint(SpringLayout.EAST, fileResultDisplayPanel, -10, SpringLayout.EAST, frame.getContentPane());
		fileResultDisplayPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, fileResultDisplayPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, fileResultDisplayPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(fileResultDisplayPanel);
		
		JPanel fileChooserPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, fileResultDisplayPanel, 164, SpringLayout.EAST, fileChooserPanel);
		springLayout.putConstraint(SpringLayout.NORTH, fileChooserPanel, 0, SpringLayout.NORTH, fileResultDisplayPanel);
		springLayout.putConstraint(SpringLayout.WEST, fileChooserPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, fileChooserPanel, 0, SpringLayout.SOUTH, fileResultDisplayPanel);
		springLayout.putConstraint(SpringLayout.EAST, fileChooserPanel, -374, SpringLayout.EAST, frame.getContentPane());
		fileChooserPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(fileChooserPanel);
		
		JLabel labelOperationDirection = new JLabel("-->");
		springLayout.putConstraint(SpringLayout.NORTH, labelOperationDirection, 120, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelOperationDirection, 40, SpringLayout.EAST, fileChooserPanel);
		springLayout.putConstraint(SpringLayout.WEST, fileResultDisplayPanel, 164, SpringLayout.EAST, fileChooserPanel);
		springLayout.putConstraint(SpringLayout.NORTH, fileChooserPanel, 0, SpringLayout.NORTH, fileResultDisplayPanel);
		springLayout.putConstraint(SpringLayout.WEST, fileChooserPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, fileChooserPanel, 0, SpringLayout.SOUTH, fileResultDisplayPanel);
		SpringLayout sl_fileResultDisplayPanel = new SpringLayout();
		fileResultDisplayPanel.setLayout(sl_fileResultDisplayPanel);
		
		JLabel labelFilesAfter = new JLabel("After");
		sl_fileResultDisplayPanel.putConstraint(SpringLayout.NORTH, labelFilesAfter, 10, SpringLayout.NORTH, fileResultDisplayPanel);
		sl_fileResultDisplayPanel.putConstraint(SpringLayout.WEST, labelFilesAfter, 70, SpringLayout.WEST, fileResultDisplayPanel);
		fileResultDisplayPanel.add(labelFilesAfter);
		springLayout.putConstraint(SpringLayout.EAST, fileChooserPanel, -374, SpringLayout.EAST, frame.getContentPane());
		SpringLayout sl_fileChooserPanel = new SpringLayout();
		fileChooserPanel.setLayout(sl_fileChooserPanel);
		
		JLabel labelFilesBefore = new JLabel("Before");
		sl_fileChooserPanel.putConstraint(SpringLayout.NORTH, labelFilesBefore, 10, SpringLayout.NORTH, fileChooserPanel);
		sl_fileChooserPanel.putConstraint(SpringLayout.WEST, labelFilesBefore, 70, SpringLayout.WEST, fileChooserPanel);
		fileChooserPanel.add(labelFilesBefore);
		labelOperationDirection.setFont(new Font("Tahoma", Font.PLAIN, 54));
		labelOperationDirection.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelOperationDirection);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{fileResultDisplayPanel, fileChooserPanel, labelOperationDirection}));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//this button opens the file chooser
		//files selected from it will be placed in the left panel to be listed and confirmed for zip/unzip operations
		//Where the final result will be available in the right panel
		JMenuItem menuFileSelectButton = new JMenuItem("Select File(s)");
		menuFileSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setDialogTitle("Choose file(s)");
				
				fileChooser.setVisible(true);
				
				fileChooser.setPreferredSize(new Dimension(200, 300));
				
				fileChooser.showOpenDialog(menuFileSelectButton);
				
				filesSelected = fileChooser.getSelectedFiles();
			
			}
		});
		menuBar.add(menuFileSelectButton);
		
		JMenuItem menuFileZipButton = new JMenuItem("Zip File(s)");
		menuFileZipButton.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(menuFileZipButton);
		
		JMenuItem MenuFileUnzipButton = new JMenuItem("Unzip File");
		MenuFileUnzipButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(MenuFileUnzipButton);
		
		JMenuItem menuExitButton = new JMenuItem("Exit");
		menuExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuExitButton.addActionListener(new CloseListener());
		menuBar.add(menuExitButton);
	
	}
	
}
