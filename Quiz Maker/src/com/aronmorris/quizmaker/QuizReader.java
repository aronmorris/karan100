package com.aronmorris.quizmaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;

public class QuizReader {
	
	private final static String QUIZ_YML_PATH = "resources\\QuizReader\\yaml.txt"; //win mode, UNIX not set yet (do with ternary?)

	/*
	 * TODO
	 * read quizzes from yml format eg:
	 * 		q1:
	 * 			option1
	 * 			option2
	 *			option3
 	 * 			...
 	 * 			ans:
 	 * 				answer
 	 * 		q2:
 	 * 			...
	 * 
	 * populate CLI or GUI with random selection of questions with options in random order
	 * 
	 * verify correct/incorrect from user input
	 * 
	 * thought: open ended questions?
	 * 
	 */
	
	QuizReader(File quizFile) {
		Yaml yamlReader = new Yaml();
		
		readFileToYamlFromStream(yamlReader, quizFile);
		
	}
	
	
	private void readFileToYamlFromStream(Yaml yaml, File file) {
		try (InputStream input = new FileInputStream(file)) {
			
			for (Object data : yaml.loadAll(input)) { //just prints out the list right now so I can see that it's reading it
				System.out.println(data);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		QuizReader qr = new QuizReader(new File(QUIZ_YML_PATH));

	}
	
}
