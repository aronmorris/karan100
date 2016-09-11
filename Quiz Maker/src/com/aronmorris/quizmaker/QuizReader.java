package com.aronmorris.quizmaker;

import java.io.IOException;

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
			
			System.out.println(xmlDoc.hasChildNodes());
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
		
		Node topicNode, answerNode, optionNode;
		
		NodeList nList;
		
		boolean questionOverflow = false;
	
		nList = xmlDoc.getElementsByTagName("question"); //get all <question> nodes, which includes topic, answer, and options inside each <question>
		
		if (numberOfQuestions > nList.getLength()) {
			System.out.println("You've requested more questions than are available. All questions will be listed.");
			questionOverflow = true;
		}
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node currentQuestion = nList.item(i); //currentQuestion is the currently
			System.out.println(currentQuestion.getTextContent());
			//quiz.addQuestion(currentQuestion.get, answer, options)
			
		}
		//TODO finish selection logic
		
		
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		QuizReader qr = new QuizReader();
		
		qr.generateQuiz(4);

	}
	
}
