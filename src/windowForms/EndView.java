package windowForms;

import javax.swing.JFrame;
import javax.swing.JLabel;

import datatypes.QuestionDatabase;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class EndView {
	// LAYOUT
	private JFrame frame;
	// AWARD
	private String award;
	// QUESTIONS
	private QuestionDatabase questionsBackup;	
	private String[] categoryBackup;
	// THREAD
	private ExecutorService exeSerPoolPrevious;

	public EndView(String award, QuestionDatabase questionsBackup, String[] categoryBackup, ExecutorService exeSerPool) {
		this.award = award;
		this.questionsBackup = questionsBackup;
		this.categoryBackup = categoryBackup;
		this.exeSerPoolPrevious = exeSerPool;
		// INITIALIZE
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(".\\resources\\millioners.png"));
		frame.setBounds(100, 100, 381, 223);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2,
				screenSize.height / 2 - frame.getSize().height / 2);

		JLabel lCongratulation = new JLabel("Congratulation!");
		lCongratulation.setForeground(new Color(176, 224, 230));
		lCongratulation.setFont(new Font("Source Serif Pro", Font.PLAIN, 20));
		lCongratulation.setBounds(109, 12, 152, 40);
		frame.getContentPane().add(lCongratulation);

		JLabel lText = new JLabel("You won " + award);
		lText.setForeground(new Color(240, 255, 255));
		lText.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		lText.setBounds(119, 62, 135, 22);
		frame.getContentPane().add(lText);

		JButton bPlayAgain = new JButton("Play again");
		bPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> new GameView(questionsBackup, categoryBackup, exeSerPoolPrevious).start()).start();
				frame.dispose();
			}
		});
		bPlayAgain.setBackground(new Color(255, 255, 255));
		bPlayAgain.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bPlayAgain.setBounds(50, 124, 100, 30);
		frame.getContentPane().add(bPlayAgain);
		bPlayAgain.setFocusPainted(false);

		JButton bMEnu = new JButton("Menu");
		bMEnu.setBackground(new Color(255, 255, 255));
		bMEnu.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bMEnu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exeSerPoolPrevious.shutdown(); // Jedyna opcja na duplikaję Threads
				StartingPageView form = new StartingPageView(questionsBackup, categoryBackup);
				form.setVisible(true);
				frame.dispose();
			}
		});
		bMEnu.setBounds(204, 124, 100, 30);
		frame.getContentPane().add(bMEnu);
		bMEnu.setFocusPainted(false);

		JLabel lBackgorund = new JLabel("");
		lBackgorund.setIcon(new ImageIcon(".\\resources\\background.png"));
		lBackgorund.setBounds(0, 0, 367, 186);
		frame.getContentPane().add(lBackgorund);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
