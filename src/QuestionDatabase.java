public class QuestionDatabase {
	
	QuestionModel[] questions = {
            new QuestionModel("Jaka jest stolica Francji?", new String[]{"Berlin", "Londyn", "Paryż", "Madryt"}, 2),
            new QuestionModel("Ile wynosi 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
    }; 
	
    public static void main(String[] args) {
           
    }
    
    public QuestionModel getQuestion() {
    	int randomQuestionIndex = (int) (Math.random() * questions.length);
        QuestionModel randomQuestion = questions[randomQuestionIndex];
        
        return randomQuestion;
    }
}
