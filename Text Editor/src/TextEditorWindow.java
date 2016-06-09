import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLDocument;


public class TextEditorWindow {

	private JFrame frame;
	
	private HashMap<String, Component> componentMap;


	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextEditorWindow window = new TextEditorWindow();
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
	public TextEditorWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		frame.getContentPane().add(scrollPane, "1, 1, 2, 2, fill, fill");
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		editorPane.setContentType("text/html");
		
		
		scrollPane.setViewportView(editorPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setBackground(Color.LIGHT_GRAY);
		
		JMenuItem newFile = new JMenuItem("New");
		menuBar.add(newFile);
		
		JMenuItem openFile = new JMenuItem("Open");
		menuBar.add(openFile);
		
		JMenuItem saveFile = new JMenuItem("Save As...");
		menuBar.add(saveFile);
		
		//TODO REMOVE THIS BITS
		editorPane.setText("...");
		
		HTMLDocument d = (HTMLDocument) editorPane.getDocument();
	
	    createComponentMap();

	    editorPane.setText(openFile((new File("/Users/Aron/Desktop/bar.txt"))));
	    
	}
	
	public void createComponentMap() {
		 componentMap = new HashMap<String,Component>();
	        Component[] components = frame.getComponents();
	        for (int i = 0; i < components.length; i++) {
	                componentMap.put(components[i].getName(), components[i]);
	        }
	}

	public Component getComponentByName(String name) {
	        if (componentMap.containsKey(name)) {
	                return (Component) componentMap.get(name);
	        }
	        else return null;
	}
	

	
	public String openFile(File file) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			
			String line = br.readLine();
			
			StringBuilder sb = new StringBuilder();	
			
			while (line != null) {
				
				sb.append(line);
				
				line = br.readLine();
			}
			
			return sb.toString();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "File not found";
		
	}
	
	//TODO use this for the "Open" menu button
	public void openFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text files", "txt");
		chooser.setFileFilter(fnef);
		chooser.setFileHidingEnabled(true);
		chooser.setCurrentDirectory(null);
		
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			
			//populateEditorPanel(f);
			
		} else {
			//user decided to not add a file
		}
	}

}
