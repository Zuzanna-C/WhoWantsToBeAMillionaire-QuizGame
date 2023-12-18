package windowForms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import backend.Questions;

import datatypes.QuestionCategory;
import datatypes.QuestionDatabase;

import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class StartingPageView {
	// LAYOUT
	private JFrame frame;
	// QUESTIONS
	private QuestionDatabase questions;
	private String[] category;
	private QuestionDatabase questionsBackup;
	private String[] categoryBackup;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartingPageView window = new StartingPageView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartingPageView() { 
		// SETUP
		this.questions = new QuestionDatabase();
		this.category = QuestionCategory.getQuestionCategory(13);
		this.questionsBackup = new QuestionDatabase();
		this.categoryBackup = QuestionCategory.getQuestionCategory(13);
		// THREAD
		Questions.getQuestions(this.questions, this.category);
		Questions.getQuestions(this.questionsBackup, this.categoryBackup);
		// INITIALIZE
		initialize();
	}
	
	public StartingPageView(QuestionDatabase questionsBackup, String[] categoryBackup) {
		this.questions = questionsBackup;
		this.category = categoryBackup; 
		// SETUP
		this.questionsBackup = new QuestionDatabase();
		this.categoryBackup = QuestionCategory.getQuestionCategory(13);
		// THREAD
		Questions.getQuestions(this.questionsBackup, this.categoryBackup);
		// INITIALIZE
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\resources\\millioners.png"));
		frame.setBounds(100, 100, 402, 214);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2,
				screenSize.height / 2 - frame.getSize().height / 2);

		JLabel lWelcome = new JLabel("Welcome in ...");
		lWelcome.setForeground(new Color(176, 224, 230));
		lWelcome.setFont(new Font("Source Serif Pro", Font.PLAIN, 20));
		lWelcome.setBounds(143, 11, 138, 29);
		frame.getContentPane().add(lWelcome);

		JButton bStats = new JButton("Stats");
		bStats.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bStats.setBackground(new Color(255, 255, 255));
		bStats.setBounds(61, 90, 114, 29);
		bStats.setFocusPainted(false);
		frame.getContentPane().add(bStats);

		JButton bNewGame = new JButton("New game");
		bNewGame.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> new GameView(questions, questionsBackup, category, categoryBackup).start()).start();
				frame.dispose();
			}
		});
		bNewGame.setBackground(new Color(255, 255, 255));
		bNewGame.setBounds(225, 93, 114, 29);
		bNewGame.setFocusPainted(false);
		frame.getContentPane().add(bNewGame);

		JButton bClose = new JButton("Close");
		bClose.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bClose.setBackground(new Color(255, 255, 255));
		bClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bClose.setBounds(290, 144, 89, 23);
		frame.getContentPane().add(bClose);

		JLabel lText = new JLabel("Who Wants To Be A Millionaire!");
		lText.setForeground(new Color(240, 255, 255));
		lText.setFont(new Font("Source Serif Pro", Font.PLAIN, 16));
		lText.setBounds(88, 33, 239, 36);
		frame.getContentPane().add(lText);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(".\\resources\\background.png"));
		lblNewLabel.setBounds(0, 0, 388, 177);
		frame.getContentPane().add(lblNewLabel);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}
