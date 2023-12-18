package windowForms;

import javax.swing.JFrame;
import javax.swing.JLabel;

import datatypes.QuestionDatabase;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class EndView {
	// LAYOUT
	private JFrame frame;
	private JLabel background;
	// AWARD
	private String award;
	// QUESTIONS
	private QuestionDatabase questionsBackup;	
	private String[] categoryBackup;

	public EndView(String award, QuestionDatabase questionsBackup, String[] categoryBackup) {
		this.award = award;
		this.questionsBackup = questionsBackup;
		this.categoryBackup = categoryBackup;
		// INITIALIZE
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(".\\resources\\millioners.png"));
		frame.setBounds(100, 100, 381, 223);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2,
				screenSize.height / 2 - frame.getSize().height / 2);

		JLabel lblNewLabel = new JLabel("Congratulation!");
		lblNewLabel.setForeground(new Color(176, 224, 230));
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.PLAIN, 20));
		lblNewLabel.setBounds(109, 12, 152, 40);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("You won " + award);
		lblNewLabel_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(119, 62, 135, 22);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Play again");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> new GameView(questionsBackup, categoryBackup).start()).start();
				frame.dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		btnNewButton.setBounds(50, 124, 100, 30);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setFocusPainted(false);

		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartingPageView form = new StartingPageView(questionsBackup, categoryBackup);
				form.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(204, 124, 100, 30);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setFocusPainted(false);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(".\\resources\\background.jpg"));
		lblNewLabel_2.setBounds(0, 0, 367, 186);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
