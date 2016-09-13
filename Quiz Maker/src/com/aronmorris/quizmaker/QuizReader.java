package com.aronmorris.quizmaker;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class QuizReader {
	
	private final static String QUIZ_XML_PATH = "D:\\Programming\\Java\\JavaWorkspace\\Projects\\Quiz Maker\\resources\\QuizReader\\quizzes.xml"; //win mode, UNIX not set yet (do with ternary?)
	
	private Document xmlDoc;
	
	QuizReader() {
		
		try {
			xmlDoc = parseXML(QUIZ_XML_PATH);
			
		} 
		catch (ParserConfigurationException e) 
	    {
	        e.printStackTrace();
	    } 
	    catch (SAXException e) 
	    {
	        e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
		
	}
	
	private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException
	{
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    
	    Document doc = db.parse(filePath);
	    
	    doc.getDocumentElement().normalize();
	    
	    return doc;
	    
	}
	
	//TODO switch from YML to XML, since XML is easier to retrieve specifics from
	//DONE
	
	public Quiz generateQuiz(int numberOfQuestions) {
		Quiz quiz = new Quiz();
		
		String topic, answer;
		ArrayList<String> options;
		
		final int TOPIC_ID = 1, ANSWER_ID = 3, OPTION_ID = 5; //NodeList is twice as large as necessary as values are only stored in odd-numbered indexes for some reason
		
		NodeList nList;
		
		boolean questionOverflow = false;
	
		nList = xmlDoc.getElementsByTagName("question"); //get all <question> nodes, which includes topic, answer, and options inside each <question>
		
		if (numberOfQuestions > nList.getLength()) {
			System.out.println("You've requested more questions than are available. All questions will be listed.");
			questionOverflow = true;
		}
		
		for (int i = 0; i < nList.getLength(); i++) {
			NodeList currentQuestion = nList.item(i).getChildNodes(); //Every question is guaranteed 3+n child nodes, in topic, answer, and options (unlimited options possible)
			
			topic = currentQuestion.item(TOPIC_ID).getTextContent();
			answer = currentQuestion.item(ANSWER_ID).getTextContent();
			
			options = new ArrayList<String>();
			
			for (int j = OPTION_ID; j < currentQuestion.getLength(); j++) {	//loop starts at 5th index, where the options list begins
				
				if (j % 2 == 1) { //indexes containing data are always odd-numbered, and only odd numbers meet this definition
					options.add(currentQuestion.item(j).getTextContent());
				}
			}
		
			String[] strOpt = new String[options.size()];
			
			options.toArray(strOpt);
			
			quiz.addQuestion(topic, answer, strOpt);
			
			
			
		}
		//TODO finish selection logic
		
		
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		QuizReader qr = new QuizReader();
		
		qr.generateQuiz(4);

	}
	
}
