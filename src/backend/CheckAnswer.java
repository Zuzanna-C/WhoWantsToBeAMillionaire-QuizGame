package backend;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import datatypes.QuestionModel;
import datatypes.QuestionDatabase;
import windowForms.EndView;

public class CheckAnswer {
	
	public boolean checkIfRightAnswer(JButton btn, int answerIndex){
		return switch (btn.getName()) {
		case "bA":
			if(answerIndex == 0) yield true;
		case "bB":
			if(answerIndex == 1) yield true;
		case "bC":
			if(answerIndex == 2) yield true;
		case "bD":
			if(answerIndex == 3) yield true;
		default:
			yield false;
		};
	}
	
	public int showQuestion(QuestionDatabase questionDatabase, int questionNumber, JButton btnA, JButton btnB, JButton btnC, JButton btnD, JLabel label1) {
		QuestionModel question = questionDatabase.getQuestionModel(questionNumber);
		
		label1.setText(question.getQuestionText());
		
		String[] answers = question.getAnswerOptions();
		btnA.setText("A: " + answers[0]);
		btnB.setText("B: " + answers[1]);
		btnC.setText("C: " + answers[2]);
		btnD.setText("D: " + answers[3]);	
		
		return question.getCorrectAnswerIndex();
	}
	
	public String getAward(JLabel label){
		String labelText = label.getText();
        int firstIndex = labelText.indexOf(' ');
        if (firstIndex != -1) {
            String extractedText = labelText.substring(firstIndex + 1);
            return extractedText;
        }
        return "";
	}
	
	public void badAnswer(JFrame frame, JLabel[] label, int playerScore, QuestionDatabase questionsBackup, String[] categoryBackup){
		
		try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		String extractedAward = "";
		
		switch (playerScore) {
		case 0:
			extractedAward = "0zl";
			break;
		case 1:
			extractedAward = getAward(label[playerScore - 1]);
			break;
		case 2,3,4,5,6:
			extractedAward = getAward(label[1]);
			break;
		case 7,8,9,10,11:
			extractedAward = getAward(label[6]);
			break;
		case 12:
			extractedAward = getAward(label[11]);
			break;
		}
			
		EndView form = new EndView(extractedAward, questionsBackup, categoryBackup);
		form.setVisible(true);
		frame.dispose();
	}

}
