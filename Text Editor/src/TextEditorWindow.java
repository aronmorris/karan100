import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.swing.plaf.FileChooserUI;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


public class TextEditorWindow {

	private JFrame frame;
	
	private JFileChooser fc;
	
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
		editorPane.setName("editorPane");
		
		HTMLEditorKit html = new HTMLEditorKit();
		
		//TODO
		/* The editor kit needs to set CSS rules for span classes that change the font and color of specific
		 * terms and any clear function name (eg "foobar()")
		 * A listener needs to be added that'll stick the correct span around each term in the document if
		 * a span isn't already there (prevents nested spans) (probably doable by including the regex characters
		 * for whitespace, which the spans won't have, being in kerning-contact with their terms
		 * 
		 */
		
		scrollPane.setViewportView(editorPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setBackground(Color.LIGHT_GRAY);
		
		JMenuItem newFile = new JMenuItem("New");
		menuBar.add(newFile);
		newFile.addActionListener(e -> { newFile(editorPane); });
		
		JMenuItem openFile = new JMenuItem("Open");
		menuBar.add(openFile);
		openFile.addActionListener(e -> { editorPane.setText(openFile()); });
		
		JMenuItem saveFile = new JMenuItem("Save As...");
		menuBar.add(saveFile);
		saveFile.addActionListener(e -> { saveFile(editorPane); });
		
	    fc = new JFileChooser();
	    
	}
	
	//highlights hardcoded keywords for now
	public void syntaxHighlight(JEditorPane editor) {
		String[] terms = editor.getText().split(" ");
		
		//htmleditorkit api use here?
		
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
	
	public void newFile(JEditorPane editor) {
		
		saveFile(editor);

		editor.setText("");
	}
	
	public void saveFile(JEditorPane editor) {
		 int returnVal = fc.showSaveDialog(frame);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
             File file = fc.getSelectedFile();
             String content = editor.getText();
         
             try {
            	//System.out.println("pre-save");
				FileWriter save = new FileWriter(file.getAbsoluteFile());
            	//System.out.println("post-save");

				BufferedWriter bs = new BufferedWriter(save);
            	//System.out.println("post-buffer");

				
				bs.write(content);
            	//System.out.println("written to file");

				bs.close();
				save.close();
				
			} catch (IOException e) {
				//auto-generated catch block
				e.printStackTrace();
			}
         }
		
	}
	

	// use this for opening files
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
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		return "File not found";
		
	}
	
	//taken from index map
	public String openFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text files", "txt");
		chooser.setFileFilter(fnef);
		chooser.setFileHidingEnabled(true);
		chooser.setCurrentDirectory(null);
		
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			
			return openFile(f);
			//populateEditorPanel(f);
			
		} else {
			//user decided to not add a file
			return null;
		}
	}

}
