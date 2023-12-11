package datatypes;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionModel {
	
	private String questionText;
    private String[] answerOptions;
    private ArrayList<Integer> answerIndexFlags = new ArrayList<Integer>(Arrays.asList(1,-1,-1,-1)); //-1 incorrect, 1 correct, 0 brak, ABCD

    public QuestionModel(String questionText, String[] answerOptions) {
    	this.questionText = questionText;
        this.answerOptions = answerOptions;
    }
    
    public QuestionModel(String questionText, String[] answerOptions, ArrayList<Integer> answerIndexFlags) {
        this(questionText, answerOptions);
        this.answerIndexFlags = answerIndexFlags;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public int getCorrectAnswerIndex() {
        return answerIndexFlags.indexOf(1);
    }

}
