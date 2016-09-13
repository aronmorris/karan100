package com.aronmorris.quizmaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class Quiz {

	/*
	 * Contains the various values the quiz might have, which are all String save for the LinkedList
	 * which contains the Options a person might choose from
	 * 
	 * Map uses integers for each question, which is composed of a Topic, Answer, and Options and is
	 * its own map
	 */
	private HashMap<Integer, HashMap<String, Object>> quizValues;
	
	//An integer count is maintained in order to select questions on a per-quiz basis and at random
	//as per the specification
	private int questionsInQuiz; 
	
	public Quiz() {
		
		quizValues = new HashMap<Integer, HashMap<String, Object>>();
		
		questionsInQuiz = 0;
		
	}
	
	public void addQuestion(String topic, String answer, String... options) {
				
		HashMap<String, Object> question = new HashMap<String, Object>();
		
		question.put("topic", topic);
		
		question.put("answer", answer);
		
		ArrayList<String> arrOpt = new ArrayList<String>(Arrays.asList(options));
		
		question.put("options", arrOpt);
		
		quizValues.put(questionsInQuiz, question);
		
		questionsInQuiz += 1; //increment to indicate that there's now more questions in the quiz
		
		System.out.println(quizValues.toString());
		
	}
	
	public String getRandomQuestion() {
		//int randSel = 
		return null;
	}
	
}
