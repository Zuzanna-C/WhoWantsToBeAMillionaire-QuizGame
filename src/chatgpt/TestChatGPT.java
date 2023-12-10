package chatgpt;

import datatypes.QuestionAndAnswers;
import datatypes.Questions;

public class TestChatGPT {

	public static void main(String[] args) {
		Questions q = null;
		try {
			q = ChatGPT.getQuestions();
		} catch (Exception e) {
			System.err.print(e);
			System.exit(-1);
		}
		
		for(int i=0; i<12; i++) {
			System.out.println(q.getQuestionAndAnswers(i).getQuestion());
			System.out.println(q.getQuestionAndAnswers(i).getCorrectAnswer());
			System.out.println(q.getQuestionAndAnswers(i).getIncorrectAnswer(QuestionAndAnswers.B));
			System.out.println(q.getQuestionAndAnswers(i).getIncorrectAnswer(QuestionAndAnswers.C));
			System.out.println(q.getQuestionAndAnswers(i).getIncorrectAnswer(QuestionAndAnswers.D));
			System.out.println();
		}

	}

}
