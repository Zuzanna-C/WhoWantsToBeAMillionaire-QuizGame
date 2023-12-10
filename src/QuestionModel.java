import java.util.Random;

public class QuestionModel {

	private String questionText;
    private String[] answerOptions;
    private int correctAnswerIndex;

    public QuestionModel(String questionText, String[] answerOptions, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

}
