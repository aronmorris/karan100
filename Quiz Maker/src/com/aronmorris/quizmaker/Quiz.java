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
	
	//populates the current (latest) question added to the Quiz
	//also a test of lambdas
	public void addQuestion(String topic, String answer, String... options) {
				
		getNewestQuestion(quizValues -> quizValues.put("topic", topic));
		
		getNewestQuestion(quizValues -> quizValues.put("answer", answer));
		
		ArrayList<String> arrOpt = new ArrayList<String>(Arrays.asList(options));
		
		getNewestQuestion(quizValues -> quizValues.put("options", arrOpt));
		
	}
	
	
	//Using an Execute Around idiom so I don't have to constantly reference the current 
	//question in the greater quiz map. Also to play with lambdas
	private <T> void getNewestQuestion(Function<HashMap<String, Object>, T> f) {
		
		HashMap<String, Object> currentQuestion = quizValues.get(questionsInQuiz);
		
		f.apply(currentQuestion);
	}
	
}
