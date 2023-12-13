package datatypes;
import java.util.ArrayList;

public class QuestionDatabase {

    private static ArrayList<QuestionModel> questions;
    
    public QuestionDatabase() {
		questions = new ArrayList<QuestionModel>();
	}
    
    public void addQuestionModel(QuestionModel questionModel) {
    	questions.add(questionModel);
	}

    public QuestionModel getQuestionModel(int index) {
        return questions.get(index);
    }

   
}
