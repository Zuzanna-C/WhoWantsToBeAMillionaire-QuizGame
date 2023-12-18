package datatypes;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestionDatabase {

    private ArrayList<QuestionModel> questions;
    
    public QuestionDatabase() {
		questions = new ArrayList<QuestionModel>(Arrays.asList(null,null,null,null,null,null,null,null,null,null,null,null,null));
	}
    
    public void setQuestionModel(int index, QuestionModel questionModel) {
    	questions.set(index, questionModel);
	}

    public QuestionModel getQuestionModel(int index) {
        return questions.get(index);
    }

    public int getNullIndex() {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i) == null) return i;
			if (questions.get(i).isNull()) return i;
		} 			
    	return -1;
	}

    public int size() {
		return 13;
	}
}
