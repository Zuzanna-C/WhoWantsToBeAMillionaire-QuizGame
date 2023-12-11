package datatypes;
import java.util.Random;

import QuestionModel;
import backend.ChatGPT;
import datatypes.QuestionAndAnswers;
import datatypes.Questions;

public class QuestionDatabase {

	// REMOVING QUESTIONS
    private static QuestionModel[] questions;
    private static int index = 0; // PO CO?
    
    // ADDING CONSTRUCTOR
    public QuestionDatabase() {
		questions = new QuestionModel[12];
	}
    
    // ADDING SETTER BY INDEX
    public void setQuestionModel(int index, QuestionModel questionModel) {
    	questions[index] = questionModel;
	}
    
    public static void main(String[] args) {}

    // DO ZMIANY (SENS DZIALANIA) np. dodanie indexu jako argument nie jako zmienna klasy
    public static QuestionModel getQuestion() {
        index++;
        if (index <= questions.length) {
            return questions[index - 1];
        } else {
            return null;
        }
    }

    private static void populateQuestionsIfNeeded() {
        if (questions == null) {
            populateQuestions();
        }
    }
    
    public static void regenerateQuestions() {
    	System.out.println("New questions have been generated");
        populateQuestions();
    }

    // DO ZMIANY
    private static void populateQuestions() {
        questions = new QuestionModel[12];
        for (int i = 0; i < 12; i++) {
            String[] answerOptions = shuffleAnswers(q.getQuestionAndAnswers(i));

            questions[i] = new QuestionModel(
                    q.getQuestionAndAnswers(i).getQuestion(),
                    answerOptions,
                    getCorrectAnswerIndex(answerOptions, q.getQuestionAndAnswers(i).getCorrectAnswer())
            );
        }
    }
    
    // DO ZMIANY
    private static String[] shuffleAnswers(QuestionAndAnswers qa) {
        String[] answers = {
                qa.getCorrectAnswer(),
                qa.getIncorrectAnswer(QuestionAndAnswers.B),
                qa.getIncorrectAnswer(QuestionAndAnswers.C),
                qa.getIncorrectAnswer(QuestionAndAnswers.D)
        };

        Random random = new Random();
        for (int i = answers.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = answers[i];
            answers[i] = answers[index];
            answers[index] = temp;
        }

        return answers;
    }

    // DO ZMIANY ???
    private static int getCorrectAnswerIndex(String[] answers, String correctAnswer) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(correctAnswer)) {
                return i;
            }
        }
        return -1;
    }
}
