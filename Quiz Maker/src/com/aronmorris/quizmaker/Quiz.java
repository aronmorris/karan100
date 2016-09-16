package com.aronmorris.quizmaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.aronmorris.quizmaker.Quiz.QuizKey;

public class Quiz {

	/*
	 * Contains the various values the quiz might have, which are all String save for the LinkedList
	 * which contains the Options a person might choose from
	 * 
	 * Map uses integers for each question, which is composed of a Topic, Answer, and Options and is
	 * its own map
	 */
	
	protected enum QuizKey { //more certain retrieval than strings
		TOPIC,
		ANSWER,
		OPTIONS
	}
	
	private LinkedList<HashMap<QuizKey, Object>> quizValues;
	
	private int correctAnswers; //the number of questions in this quiz answered correctly
	
	private final int QUIZ_SIZE;
	
	private Scanner input;
	
	public Quiz(int size) {
		
		quizValues = new LinkedList<HashMap<QuizKey, Object>>();	//TODO modify this so it's not clumsily casting back and forth
		
		correctAnswers = 0; 
		
		QUIZ_SIZE = size;
		
		input = new Scanner(System.in);
		
	}
	
	protected void addQuestion(String topic, String answer, String... options) {
				
		HashMap<QuizKey, Object> question = new HashMap<QuizKey, Object>();
		
		question.put(QuizKey.TOPIC, topic);
		
		question.put(QuizKey.ANSWER, answer);
		
		ArrayList<String> arrOpt = new ArrayList<String>(Arrays.asList(options));
		
		question.put(QuizKey.OPTIONS, arrOpt);
		
		quizValues.add(question);
		
				
	}
	
	//Fires off the number of questions requested by the user for this quiz, waiting for answers from each one
	//At the end, sums up the number of correct answers out of the total questions
	public void takeQuiz() {
				
		Collections.shuffle(quizValues); //randomizes the order of the questions in the list
		
		//List<HashMap<QuizKey, Object>> temp =  quizValues.subList(0, QUIZ_SIZE + 1); //cuts the list down to the correct size requested by the QuizReader, 0-SIZE inclusive
		
		quizValues = new LinkedList<HashMap<QuizKey, Object>>( quizValues.subList(0, QUIZ_SIZE)); //cuts the list down to the correct size requested by the QuizReader
		
		int totalQuestions = quizValues.size();
		
		do {
			getRandomQuestion();
		} while (!quizValues.isEmpty());
		
		System.out.printf("You got %d/%d questions correct!%n", correctAnswers, totalQuestions); //reports total correct from all taken
		
	}
	
	//Selects a question, prints it to the user, and handles IO for responses
	private boolean getRandomQuestion() {
				
		//TODO CRITICAL
		/*
		 * The use of randSel would work if the quizValues data structure was a list, not a map, since it would access a random element.
		 * As is, it'll at random cause a bug where a previously randomly generated value will be generated again, but as the structure
		 * is a map, the key resolves to a null value and throws a null pointer exception.
		 * 
		 * Fix: Change the map of ints to a linkedlist and run it through Collections.shuffle. Then, each time a question is pulled out
		 * the list will shrink to fit, so the same random can be called several times.
		 * 
		 * Thought: Why rely on random at all if the list of questions is already randomized and can simply be traversed?
		 */
		
		//Implementing the above
		HashMap<QuizKey, Object> question = quizValues.poll(); //retrieves the first question and removes it from the list. The list is shuffled ahead of time
		
		
		boolean questionCorrect = false;
		
		try { //use of the scanner in this method is rudimentary but it works barring going to a UI design, also, using it as a try-with-resource so it closes itself
			  //Fixed a bug with the scanner closing System.in when closed, causing it to be unavailable for future scanners
			
			printQuestion(question);
			
			String ans = (String) input.nextLine();
			
			if (ans.equalsIgnoreCase((String) question.get(QuizKey.ANSWER))) {
				System.out.println("Correct!");
				questionCorrect = true;
			} else {
				System.out.println("Incorrect!");
				questionCorrect = false;
			}
			
		} finally {
			
			correctAnswers += (questionCorrect ? 1 : 0); //increment correct answers if the question was answered correctly
			
		}
		
		return questionCorrect;
	
	}
	
	//prints the question to the user, then returns control to whatever is using the map for the answer
	private void printQuestion(HashMap<QuizKey, Object> question) {
		
		@SuppressWarnings("unchecked")
		ArrayList<String> options = (ArrayList<String>) question.get(QuizKey.OPTIONS);
		
		options.add((String) question.get(QuizKey.ANSWER));
		
		long seed = System.nanoTime(); //get a seed from system time
		
		Collections.shuffle(options, new Random(seed)); //and use it in generating a new random value such that each list is uniquely shuffled 
		
		System.out.println(question.get(QuizKey.TOPIC));
		
		for (String s : options) {
			System.out.println(s);
		}
		
		System.out.println("Enter answer now from the options above.");
	
	}
	
}
