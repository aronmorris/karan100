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
		
		Node topicNode, answerNode; 
		ArrayList<Node> optionNode;
		
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
			
			topicNode = currentQuestion.item(TOPIC_ID);
			answerNode = currentQuestion.item(ANSWER_ID);
			
			optionNode = new ArrayList<Node>();
			
			for (int j = OPTION_ID; j < currentQuestion.getLength(); j++) {	
				
				if (j % 2 == 1) {
					optionNode.add(currentQuestion.item(j));
				}
			}
			
			for (Node n : optionNode) System.out.println(n.getTextContent());
			
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
