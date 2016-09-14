package com.aronmorris.quizmaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Quiz {

	/*
	 * Contains the various values the quiz might have, which are all String save for the LinkedList
	 * which contains the Options a person might choose from
	 * 
	 * Map uses integers for each question, which is composed of a Topic, Answer, and Options and is
	 * its own map
	 */
	
	private enum QuizKey { //more certain retrieval than strings
		TOPIC,
		ANSWER,
		OPTIONS
	}
	
	private HashMap<Integer, HashMap<QuizKey, Object>> quizValues;
	
	//An integer count is maintained in order to select questions on a per-quiz basis and at random
	//as per the specification
	private int questionsInQuiz; 
	
	private int correctAnswers; //the number of questions in this quiz answered correctly
	
	public Quiz() {
		
		quizValues = new HashMap<Integer, HashMap<QuizKey, Object>>();	//TODO modify this so it's not clumsily casting back and forth
		
		questionsInQuiz = 0;
		
		correctAnswers = 0; 
		
	}
	
	protected void addQuestion(String topic, String answer, String... options) {
				
		HashMap<QuizKey, Object> question = new HashMap<QuizKey, Object>();
		
		question.put(QuizKey.TOPIC, topic);
		
		question.put(QuizKey.ANSWER, answer);
		
		ArrayList<String> arrOpt = new ArrayList<String>(Arrays.asList(options));
		
		question.put(QuizKey.OPTIONS, arrOpt);
		
		quizValues.put(questionsInQuiz, question);
		
		questionsInQuiz += 1; //increment to indicate that there's now more questions in the quiz
				
	}
	
	//Fires off the number of questions requested by the user for this quiz, waiting for answers from each one
	//At the end, sums up the number of correct answers out of the total questions
	public void takeQuiz() {
		
		int totalQuestions = quizValues.size();
		
		do {
			getRandomQuestion();
		} while (!quizValues.isEmpty());
		
		System.out.printf("You got %d/%d questions correct!", correctAnswers, totalQuestions);
		
	}
	
	//Selects a question, prints it to the user, and handles IO for responses
	private boolean getRandomQuestion() {
				
		Random randSel = new Random();
		
		int qKey = randSel.nextInt(questionsInQuiz);
		
		HashMap<QuizKey, Object> question = quizValues.get(qKey); 
		
		boolean questionCorrect = false;
		
		try (Scanner sc = new Scanner(System.in)) { //use of the scanner in this method is rudimentary but it works barring going to a UI design, also, using it as a try-with-resource so it closes itself
			
			printQuestion(question);
			
			String ans = sc.nextLine();
			
			if (ans.equalsIgnoreCase((String) question.get(QuizKey.ANSWER))) {
				System.out.println("Correct!");
				questionCorrect = true;
			} else {
				System.out.println("False!");
				questionCorrect = false;
			}
			
		} finally {
			quizValues.remove(qKey); //remove the question from the rotation so, right or wrong, it won't get asked twice unless the quiz is rebuilt by asking the QuizReader for more questions
			
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
		
		Collections.shuffle(options, new Random(seed)); //and use it in generating a new random value such that each list is uniquely shuffled ([1 + 
		
		System.out.println(question.get(QuizKey.TOPIC));
		
		for (String s : options) {
			System.out.println(s);
		}
		
		System.out.println("Enter answer now from the options above.");
	}
	
}
