import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckAnswer {

	public static void main(String[] args) {

	}
	
	public boolean checkIfRightAnswer(JButton btn, int answerIndex){
		switch (btn.getName()) {
		case "bA":
			if (answerIndex == 0) {
				return true;
			}
			break;
		case "bB":
			if (answerIndex == 1) {
				return true;
			}
			break;
		case "bC":
			if (answerIndex == 2) {
				return true;
			}
			break;
		case "bD":
			if (answerIndex == 3) {
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}
	
	public int showQuestion(JButton btnA, JButton btnB, JButton btnC, JButton btnD, JLabel label1) {
		QuestionDatabase obj = new QuestionDatabase();
		QuestionModel question = obj.getQuestion();
		
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
	
	public void badAnswer(JFrame frame, JLabel[] label, int playerScore){
		
		try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		String extractedAward = "";
		if (playerScore == 0) {
			extractedAward = "0zl";		
		}
		else {
			extractedAward = getAward(label[playerScore - 1]);					
		}
		
		EndView form = new EndView(extractedAward);
		form.setVisible(true);
		frame.dispose();
	}

}
