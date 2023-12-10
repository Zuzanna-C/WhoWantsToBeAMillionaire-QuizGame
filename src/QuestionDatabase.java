import java.util.Random;

import chatgpt.ChatGPT;
import datatypes.QuestionAndAnswers;
import datatypes.Questions;

public class QuestionDatabase {

    private static Questions q;
    private static QuestionModel[] questions;
    private static int index = 0;

    static {
        try {
            q = ChatGPT.getQuestions();
            populateQuestionsIfNeeded();
        } catch (Exception e) {
            System.err.print(e);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {}

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

    private static int getCorrectAnswerIndex(String[] answers, String correctAnswer) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(correctAnswer)) {
                return i;
            }
        }
        return -1;
    }
}
