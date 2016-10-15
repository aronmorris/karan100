import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.DefaultListModel;


public class SnippetIO {

	//location of the file storing the snippets - this is static as there's meant to be only one snippets file
	private final String FILE_LOC = ".\\snippets.txt";
	
	private File snippets = new File(FILE_LOC);
	
	private final DefaultListModel<String> model;
	
	/*
	 * TODO
	 * This class will read the snippets.txt file and write its contents to the list model, with each line going into the list, separated by newline characters
	 * It will also read from the list to write to the file. Appended writing isn't needed since the file only stores the current contents of the list, and if an
	 * item is deleted from the list, it's clearly no longer desired to be kept
	 */
	
	public SnippetIO (DefaultListModel<String> model) {
		this.model = model;
	}
	
	//on load, write the contents of the text file to the model when the program starts
	public void writeToModel() {
		
		ArrayList<String> snippetsFromFile = new ArrayList<String>();
		
		try (BufferedReader input = new BufferedReader(new FileReader(snippets))) {
			
			String line;
			
			while((line = input.readLine()) != null && line.length() != 0) {
				snippetsFromFile.add(line);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Snippets file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO exception occurred while reading from file.");
			e.printStackTrace();
		}
		
		for (String snippet : snippetsFromFile) {
			model.addElement(snippet);
		}
		
	}
	
	public void writeToFile() {
		 //using try-with-resource to save writing space & to handle cleanup
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_LOC, false))) {
			
			for (int i = 0; i < model.size(); i++) {
				writer.write(model.get(i));
				writer.newLine();
				writer.flush();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
