package datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuestionModel {
	
	public final static int A=0;
	public final static int B=1;
	public final static int C=2;
	public final static int D=3;
	
	private String questionText;
    private String[] answerOptions;
    private ArrayList<Integer> answerIndexFlags = new ArrayList<Integer>(Arrays.asList(1,-1,-1,-1)); //-1 incorrect, 1 correct, 0 brak, ABCD

    public QuestionModel() {}
    
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

    public ArrayList<Integer> getAnswerIndexFlags() {
		return answerIndexFlags;
	}
    
    public void setAnswerIndexFlag(int index, int num){
    	answerIndexFlags.set(index, num);
    }
    
    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public int getCorrectAnswerIndex() {
        return answerIndexFlags.indexOf(1);
    }
    
    public boolean isNull() {
		if (questionText == null) return true;
		return false;
	}

    // Wzorzec Builder
    public QuestionModel shuffleAnswerOptions() {
        Random r = new Random();
    	if (r.nextInt(101) <= 25) return this;
        int index = r.nextInt(4);
        String temp = answerOptions[A];
        answerOptions[A] = answerOptions[index];
        answerIndexFlags.set(A, answerIndexFlags.get(index));
        answerOptions[index] = temp;
        answerIndexFlags.set(index, 1);
        return this;
    }
}
