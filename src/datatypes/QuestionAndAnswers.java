package datatypes;

import java.util.ArrayList;

public class QuestionAndAnswers {
	
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	
	private String question;
	private ArrayList<String> answers;
	
	public QuestionAndAnswers() {
		answers = new ArrayList<String>();
	}
	public QuestionAndAnswers(String qusetion, String[] answers) {
		this();
		this.question = qusetion;
		for (String answer : answers) {
			this.answers.add(answer);
		}
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getCorrectAnswer() {
		return answers.get(0);
	}
	
	public String getIncorrectAnswer(int answerFlag) {
		return answers.get(answerFlag);
	}
	
}
