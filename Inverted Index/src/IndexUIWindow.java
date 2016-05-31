import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;


public class IndexUIWindow {

	private JFrame frame;
	private JTextField textField;
	private IndexMap index = new IndexMap();
	private JList<String> list;
	private DefaultListModel<String> listModel;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					IndexUIWindow window = new IndexUIWindow();
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
	public IndexUIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Inverted Index UI");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(e -> { addToList(); });
		
		JButton btnChooseFile = new JButton("Add File");
		btnChooseFile.addActionListener(e -> { chooseFile(); }); //open file chooser dialog
			
		listModel = new DefaultListModel<String>();
		
		list = new JList<String>(listModel);
		
		list.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.LIGHT_GRAY));		
		
		//autogenerated
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnChooseFile, 0, 0, Short.MAX_VALUE)
						.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChooseFile)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	
	public void chooseFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text files", "txt");
		chooser.setFileFilter(fnef);
		chooser.setFileHidingEnabled(true);
		chooser.setCurrentDirectory(null);
		
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			
			index.index(f); //adds file 
			
		} else {
			//user decided to not add a file
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<File, Integer>[] searchString() {
		String[] tokens = IndexMap.tokenize(textField.getText());
		ArrayList<HashMap<File, Integer>> toArr = new ArrayList<HashMap<File, Integer>>();
		
		for (String token : tokens) {
			HashMap<File, Integer> valueMap = index.getOccurrences(token);
			toArr.add(valueMap);
		}
		
		HashMap<File, Integer>[] rArr = new HashMap[toArr.size()];
		
		toArr.toArray(rArr);
		
		return rArr;
		
	}
	
	public void addToList() {
		
		listModel.removeAllElements();
		
		HashMap<File, Integer> arr[] = searchString();
			
		for (HashMap<File, Integer> map : arr) {
			for (File file : map.keySet()) {
				StringBuilder sb = new StringBuilder();
				sb.append(file.getName());
				sb.append(": ");
				sb.append(map.get(file));
				sb.append(" counts of term.");
				
				listModel.addElement(sb.toString());
			}
		}
		
	}
	
	
}
