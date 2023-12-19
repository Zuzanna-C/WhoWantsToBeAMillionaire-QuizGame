package backend;

import java.beans.PropertyVetoException;

import datatypes.QuestionModel;


public class GetQuestion implements Runnable {

	private String category;
	private QuestionModel questionModel;
	private final int maxAttemptTrys = 3; 
	
	public GetQuestion(String category, QuestionModel questionModel) {
		this.category = category;
		this.questionModel = questionModel;
	}
	
	@Override
	public void run() {
		for (int i = this.maxAttemptTrys; i > 0; i--) {
			try {
				questionModel = ChatGPT.getQuestion(category);
				System.out.println(questionModel.getQuestionText());
				break;
			} catch (Exception e) {
				continue;
			}
		}
	}

	public static void main(String[] args) {
		QuestionModel v = new QuestionModel();
		new Thread(new GetQuestion("Gry Komputerowe", v)).start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(v.getQuestionText());

	}

}
