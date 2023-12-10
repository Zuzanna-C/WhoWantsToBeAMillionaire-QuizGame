package datatypes;

import java.util.ArrayList;

public class Questions {
	
	public static final int Q1 = 0;
	public static final int Q2 = 1;
	public static final int Q3 = 2;
	public static final int Q4 = 3;
	public static final int Q5 = 4;
	public static final int Q6 = 5;
	public static final int Q7 = 6;
	public static final int Q8 = 7;
	public static final int Q9 = 8;
	public static final int Q10 = 9;
	public static final int Q11 = 10;
	public static final int Q12 = 11;
	
	private ArrayList<QuestionAndAnswers> questions;
	
	public Questions() {
		questions = new ArrayList<>();
	}
	
	public void addQuestionAndAnswers(QuestionAndAnswers qusetionAndAnswers) {
		this.questions.add(qusetionAndAnswers);
	}
	
	public QuestionAndAnswers getQuestionAndAnswers(int questionsFlag) {
		return questions.get(questionsFlag);
	}

}
