package windowForms;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;

import backend.Questions;
import datatypes.QuestionDatabase;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class Loading {

	private JFrame frmLoading;
	// QUESTIONS
	private QuestionDatabase questions;
	private String[] category;
	private int questionNumber;

	public Loading(int questionNumber, QuestionDatabase questions, String[] category) {
		this.questionNumber = questionNumber;
		this.questions = questions;
		this.category = category;
		initialize();
		frmLoading.setVisible(true);
	}

	private void initialize() {
		frmLoading = new JFrame();
		frmLoading.setResizable(false);
		frmLoading.setTitle("Loading");
		frmLoading.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\millioners.png"));
		frmLoading.setBounds(100, 100, 241, 122);
		frmLoading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoading.getContentPane().setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmLoading.setLocation(screenSize.width / 2 - frmLoading.getSize().width / 2,
				screenSize.height / 2 - frmLoading.getSize().height / 2);
		
		JLabel lLoading = new JLabel("Loading...");
		lLoading.setForeground(new Color(240, 255, 255));
		lLoading.setFont(new Font("Source Serif Pro", Font.PLAIN, 16));
		lLoading.setBounds(74, 11, 106, 28);
		frmLoading.getContentPane().add(lLoading);
		
		JLabel lPleaseWait = new JLabel("Please wait");
		lPleaseWait.setForeground(new Color(240, 255, 255));
		lPleaseWait.setFont(new Font("Source Serif Pro", Font.PLAIN, 14));
		lPleaseWait.setBounds(74, 33, 85, 28);
		frmLoading.getContentPane().add(lPleaseWait);
		
		JLabel lBackground = new JLabel("");
		lBackground.setIcon(new ImageIcon(".\\resources\\background.png"));
		lBackground.setBounds(0, 0, 227, 85);
		frmLoading.getContentPane().add(lBackground);
	}
	
	public boolean isLoad() {
		Questions.getMissingQuestions(questions, category);
		long time_start = System.currentTimeMillis();
		while (System.currentTimeMillis() - time_start < 4*Questions.sleepTime) {
			if (questions.getQuestionModel(this.questionNumber) != null) {
				frmLoading.dispose();
				return true;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		new LoadingWithProblems("Serwis obecnie niedostępny");
		frmLoading.dispose();
		return false;
	}
}
