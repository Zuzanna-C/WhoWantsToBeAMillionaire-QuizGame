package backend;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datatypes.QuestionDatabase;

public class Questions {
	// LOGGER
	private static Logger logger = LogManager.getLogger(Questions.class.getName());
	private static int threadNumber = (Runtime.getRuntime().availableProcessors() > 1 ? Runtime.getRuntime().availableProcessors() - 1 : 1);
	public static int sleepTime = 8000;

	public static void getQuestion(int questionNumber, QuestionDatabase questionDatabase, String[] category) {
		loop: for (int tryNumber = 0; tryNumber < 3; tryNumber++) {
			try {
				questionDatabase.setQuestionModel(questionNumber, ChatGPT.getQuestion(category[questionNumber]));
				logger.info("Question " + (questionNumber+1) + " - success.");
				break loop;
			} catch (Exception e) {
				logger.error("Error in getting question " + (questionNumber+1) + " try: " + tryNumber + " thread: "
						+ Thread.currentThread().getName() + ".");
				logger.error(e.getMessage());
				if (e.getMessage().hashCode() == "No internet connection".hashCode()) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e1) {
						logger.error("Thread " + Thread.currentThread().getName() + " InterruptedException");
						continue loop;
					}
				}
				continue loop;
			}
		}
	}

	public static void getQuestions(QuestionDatabase questions, String[] category) {
		ExecutorService pool = Executors.newFixedThreadPool(threadNumber);
		for (int questionNumber = 0; questionNumber < 13; questionNumber++) {
			int questionNumberF = questionNumber;
			pool.submit(() -> getQuestion(questionNumberF, questions, category));
		}
		pool.shutdown();
	}
	
	public static void getMissingQuestions(QuestionDatabase questions, String[] category)  {
		ExecutorService pool = Executors.newFixedThreadPool(threadNumber);
		for (int questionNumber = 0; questionNumber < questions.size(); questionNumber++) {
			if (questions.getQuestionModel(questionNumber) == null || questions.getQuestionModel(questionNumber).isNull()) {
				int questionNumberF = questionNumber;
				pool.submit(() -> getQuestion(questionNumberF, questions, category));
			}
		}
		pool.shutdown();
	}

	public static boolean isQuestionsOK(QuestionDatabase questions) {
		if (questions.getNullIndex() == -1) return true;
		return false;
	}

}
