package windowForms;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import backend.AudienceHelpPlot;
import backend.ChangingColors;
import backend.ChatGPT;
import backend.CheckAnswer;
import datatypes.QuestionCategory;
import datatypes.QuestionDatabase;
import datatypes.QuestionModel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;

public class GameView {
	// LOGGER
	private static Logger logger = LogManager.getLogger(GameView.class.getName());
	// LAYOUT
	private JFrame frame;
	private JLabel[] labels = new JLabel[12];
	private JLabel lQuestion = new JLabel("");
	private JButton[] btns = new JButton[4];
	private JButton bA = new JButton("A:");
	private JButton bB = new JButton("B:");
	private JButton bC = new JButton("C:");
	private JButton bD = new JButton("D:");
	// GAME
	private int playerScore = 0;
	private int correctAnswer = 0;
	private CheckAnswer check = new CheckAnswer();
	// QUESTIONS
	private QuestionDatabase questions;
	private String[] category;
	private QuestionDatabase questionsBackup;
	private String[] categoryBackup;

	public GameView(QuestionDatabase questionsBackup, String[] categoryBackup) {
		this.questions = questionsBackup;
		this.category = categoryBackup;
		// TEST QUESTIONS
		if (!isQuestionsOK(this.questions))  { 
			logger.fatal("NO QUESTIONS");
			// TEMPLATKA BRAK INTERNETU
		}
		// SETUP
		this.questionsBackup = new QuestionDatabase();
		this.categoryBackup = QuestionCategory.getQuestionCategory(13);
		// THREAD
		getQuestions(this.questionsBackup, this.categoryBackup);
		// INITIALIZE
		initialize();
		// ??
		correctAnswer = check.showQuestion(this.questions, playerScore, bA, bB, bC, bD, lQuestion);
	}

	public GameView(QuestionDatabase questions, QuestionDatabase questionsBackup, String[] category, String[] categoryBackup) {
		this.questions = questions;
		this.questionsBackup = questionsBackup;
		this.category = category;
		this.categoryBackup = categoryBackup;
		// TEST QUESTIONS
		if (!isQuestionsOK(this.questions))  { 
			logger.fatal("NO QUESTIONS");
			// TEMPLATKA BRAK INTERNETU
		}
		if (!isQuestionsOK(this.questionsBackup)) getMissingQuestions(this.questionsBackup, this.categoryBackup);
		// INITIALIZE
		initialize();
		// ??
		correctAnswer = check.showQuestion(this.questions, playerScore, bA, bB, bC, bD, lQuestion);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\resources\\millioners.png"));
		frame.setTitle("Who wants to be a Millionare");
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 632, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2,
				screenSize.height / 2 - frame.getSize().height / 2);

		lQuestion.setForeground(new Color(240, 255, 255));
		lQuestion.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		lQuestion.setBounds(29, 158, 419, 44);
		frame.getContentPane().add(lQuestion);

		JLabel l1 = new JLabel("1. 500zł");
		l1.setForeground(new Color(240, 255, 255));
		l1.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l1.setBounds(500, 300, 96, 14);
		frame.getContentPane().add(l1);

		JLabel l2 = new JLabel("2. 1000zł");
		l2.setForeground(new Color(240, 255, 255));
		l2.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l2.setBounds(500, 275, 96, 14);
		frame.getContentPane().add(l2);

		JLabel l3 = new JLabel("3. 2000zł");
		l3.setForeground(new Color(240, 255, 255));
		l3.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l3.setBounds(500, 250, 96, 14);
		frame.getContentPane().add(l3);

		JLabel l4 = new JLabel("4. 5000zł");
		l4.setForeground(new Color(240, 255, 255));
		l4.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l4.setBounds(500, 225, 96, 14);
		frame.getContentPane().add(l4);

		JLabel l5 = new JLabel("5. 10 000zł");
		l5.setForeground(new Color(240, 255, 255));
		l5.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l5.setBounds(500, 200, 96, 14);
		frame.getContentPane().add(l5);

		JLabel l6 = new JLabel("6. 20 000zł");
		l6.setForeground(new Color(240, 255, 255));
		l6.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l6.setBounds(500, 175, 96, 14);
		frame.getContentPane().add(l6);

		JLabel l7 = new JLabel("7. 40 000zł");
		l7.setForeground(new Color(240, 255, 255));
		l7.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l7.setBounds(500, 150, 96, 14);
		frame.getContentPane().add(l7);

		JLabel l8 = new JLabel("8. 75 000zł");
		l8.setForeground(new Color(240, 255, 255));
		l8.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l8.setBounds(500, 125, 96, 14);
		frame.getContentPane().add(l8);

		JLabel l9 = new JLabel("9. 125 000zł");
		l9.setForeground(new Color(240, 255, 255));
		l9.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l9.setBounds(500, 100, 96, 14);
		frame.getContentPane().add(l9);

		JLabel l10 = new JLabel("10. 250 000zł");
		l10.setForeground(new Color(240, 255, 255));
		l10.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l10.setBounds(500, 75, 96, 14);
		frame.getContentPane().add(l10);

		JLabel l11 = new JLabel("11. 500 000zł");
		l11.setForeground(new Color(240, 255, 255));
		l11.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l11.setBounds(500, 50, 96, 14);
		frame.getContentPane().add(l11);

		JLabel l12 = new JLabel("12. 1 000 000zł");
		l12.setForeground(new Color(240, 255, 255));
		l12.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		l12.setBounds(500, 25, 96, 14);
		frame.getContentPane().add(l12);

		labels[0] = l1;
		labels[1] = l2;
		labels[2] = l3;
		labels[3] = l4;
		labels[4] = l5;
		labels[5] = l6;
		labels[6] = l7;
		labels[7] = l8;
		labels[8] = l9;
		labels[9] = l10;
		labels[10] = l11;
		labels[11] = l12;

		JButton b5050 = new JButton("");
		b5050.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fiftyFifty();
				setImage(b5050, "5050Used");
				setButtonUnclickable(b5050);
			}
		});
		b5050.setBackground(Color.LIGHT_GRAY);
		b5050.setBounds(26, 115, 45, 45);

		setImage(b5050, "5050");
		frame.getContentPane().add(b5050);
		b5050.repaint();
		b5050.setFocusPainted(false);

		JButton bChangeQuestion = new JButton("");
		bChangeQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check.showQuestion(questions, playerScore, btns[0], btns[1], btns[2], btns[3], lQuestion);
				setImage(bChangeQuestion, "switchUsed");
				setButtonUnclickable(bChangeQuestion);
			}
		});
		bChangeQuestion.setBackground(Color.LIGHT_GRAY);
		bChangeQuestion.setBounds(166, 115, 45, 45);
		setImage(bChangeQuestion, "switch");
		frame.getContentPane().add(bChangeQuestion);

		JButton bPublicity = new JButton("");
		bPublicity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print(b5050.getActionListeners().length == 0);
				if (b5050.getActionListeners().length == 0)
					AudienceHelpPlot.createPlot2Answers(questions.getQuestionModel(playerScore).getAnswerIndexFlags());
				else
					AudienceHelpPlot.createPlot4Answers(questions.getQuestionModel(playerScore).getAnswerIndexFlags());
				setImage(bPublicity, "publicityUsed");
				setButtonUnclickable(bPublicity);
			}
		});
		bChangeQuestion.setFocusPainted(false);

		bPublicity.setBackground(Color.LIGHT_GRAY);
		bPublicity.setBounds(96, 115, 45, 45);
		frame.getContentPane().add(bPublicity);
		// wstawianie obrazu
		setImage(bPublicity, "publicity");
		bPublicity.setFocusPainted(false);

		bA.setName("bA");
		bA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangingColors coloring = new ChangingColors();

				if (check.checkIfRightAnswer(bA, correctAnswer)) {
					playerScore++;
					coloring.changeLabelColorGreen(labels[playerScore - 1]);
					if (playerScore == 12) {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
						return;
					}
					correctAnswer = check.showQuestion(questions, playerScore, bA, bB, bC, bD, lQuestion);
				} else {
					coloring.changeLabelColorRed(labels[playerScore]);
					labels[playerScore].repaint();
					coloring.showRightAnswer(correctAnswer, btns);

					SwingUtilities.invokeLater(() -> {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
					});
				}
			}
		});
		bA.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bA.setBackground(new Color(255, 255, 255));
		bA.setBounds(29, 213, 167, 44);
		frame.getContentPane().add(bA);
		bA.setFocusPainted(false);

		bB.setName("bB");
		bB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangingColors coloring = new ChangingColors();

				if (check.checkIfRightAnswer(bB, correctAnswer)) {
					playerScore++;
					coloring.changeLabelColorGreen(labels[playerScore - 1]);
					if (playerScore == 12) {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
						return;
					}
					correctAnswer = check.showQuestion(questions, playerScore, bA, bB, bC, bD, lQuestion);
				} else {
					coloring.changeLabelColorRed(labels[playerScore]);
					labels[playerScore].repaint();
					coloring.showRightAnswer(correctAnswer, btns);

					SwingUtilities.invokeLater(() -> {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
					});
				}
			}
		});
		bB.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bB.setBackground(new Color(255, 255, 255));
		bB.setBounds(281, 213, 167, 44);
		frame.getContentPane().add(bB);
		bB.setFocusPainted(false);

		bC.setName("bC");
		bC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangingColors coloring = new ChangingColors();

				if (check.checkIfRightAnswer(bC, correctAnswer)) {
					playerScore++;
					coloring.changeLabelColorGreen(labels[playerScore - 1]);
					if (playerScore == 12) {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
						return;
					}
					correctAnswer = check.showQuestion(questions, playerScore, bA, bB, bC, bD, lQuestion);
				} else {
					coloring.changeLabelColorRed(labels[playerScore]);
					labels[playerScore].repaint();
					coloring.showRightAnswer(correctAnswer, btns);

					SwingUtilities.invokeLater(() -> {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
					});
				}
			}
		});
		bC.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bC.setBackground(new Color(255, 255, 255));
		bC.setBounds(29, 276, 167, 44);
		frame.getContentPane().add(bC);
		bC.setFocusPainted(false);

		bD.setName("bD");
		bD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangingColors coloring = new ChangingColors();

				if (check.checkIfRightAnswer(bC, correctAnswer)) {
					playerScore++;
					coloring.changeLabelColorGreen(labels[playerScore - 1]);
					if (playerScore == 12) {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
						return;
					}
					correctAnswer = check.showQuestion(questions, playerScore, bA, bB, bC, bD, lQuestion);
				} else {
					coloring.changeLabelColorRed(labels[playerScore]);
					labels[playerScore].repaint();
					coloring.showRightAnswer(correctAnswer, btns);

					SwingUtilities.invokeLater(() -> {
						check.badAnswer(frame, labels, playerScore, questionsBackup, categoryBackup);
					});
				}
			}
		});
		bD.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bD.setBackground(new Color(255, 255, 255));
		bD.setBounds(281, 276, 167, 44);
		frame.getContentPane().add(bD);
		bD.setFocusPainted(false);

		btns[0] = bA;
		btns[1] = bB;
		btns[2] = bC;
		btns[3] = bD;

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\resources\\background.png"));
		lblNewLabel.setBounds(0, 0, 618, 354);
		frame.getContentPane().add(lblNewLabel);

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public void setImage(JButton button, String image) {
		String filePath = ".\\resources\\" + image + ".png";
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(button.getWidth(),
				button.getHeight(), Image.SCALE_DEFAULT));
		button.setIcon(imageIcon);

	}

	public void fiftyFifty() {
		ArrayList<Integer> numList = new ArrayList<>();
		numList.add(0);
		numList.add(1);
		numList.add(2);
		numList.add(3);
		int toRemove = numList.indexOf(correctAnswer);

		if (toRemove != -1) {
			numList.remove(toRemove);
		} else {
			System.out.println("Nie znaleziono liczby do usunięcia");
		}

		int index1 = losujLiczbe(numList);
		int index2 = losujLiczbe(numList);

		questions.getQuestionModel(playerScore).setAnswerIndexFlag(index1, 0);
		questions.getQuestionModel(playerScore).setAnswerIndexFlag(index2, 0);

		btns[index1].setText("");
		btns[index2].setText("");
	}

	private int losujLiczbe(ArrayList<Integer> lista) {
		Random random = new Random();
		int indeks = random.nextInt(lista.size());
		return lista.remove(indeks);
	}

	private static void setButtonUnclickable(JButton button) {
		for (ActionListener al : button.getActionListeners()) {
			button.removeActionListener(al);
		}
	}
	
	// WADKI
	private static void getQuestion(int questionNumber, QuestionDatabase questionDatabase, String[] category) {
		loop: for (int tryNumber = 0; tryNumber < 3; tryNumber++) {
			try {
				questionDatabase.setQuestionModel(questionNumber, ChatGPT.getQuestion(category[questionNumber]));
				logger.info("Question " + (questionNumber+1) + " - success.");
				break loop;
			} catch (Exception e) {
				logger.error("Error in getting question try: " + tryNumber + " thread: "
						+ Thread.currentThread().getName() + ".");
				logger.error(e.getMessage());
				if (e.getMessage().hashCode() == "No internet connection".hashCode()) {

					try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						logger.error("Error in Thread.sleep().");
						continue loop;
					}
				}
				continue loop;
			}
		}
	}

	private static void getQuestions(QuestionDatabase questions, String[] category) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		for (int questionNumber = 0; questionNumber < 13; questionNumber++) {
			int questionNumberF = questionNumber;
			pool.submit(() -> getQuestion(questionNumberF, questions, category));
		}
		pool.shutdown();
	}
	
	private static void getMissingQuestions(QuestionDatabase questions, String[] category)  {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		for (int questionNumber = 0; questionNumber < questions.size(); questionNumber++) {
			if (questions.getQuestionModel(questionNumber) == null || questions.getQuestionModel(questionNumber).isNull()) {
				int questionNumberF = questionNumber;
				pool.submit(() -> getQuestion(questionNumberF, questions, category));
			}
		}
		pool.shutdown();
	}

	private static boolean isQuestionsOK(QuestionDatabase questions) {
		if (questions.getNullIndex() == -1) return true;
		return false;
	}
	
}
