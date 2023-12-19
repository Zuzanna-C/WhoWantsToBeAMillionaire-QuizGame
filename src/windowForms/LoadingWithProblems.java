package windowForms;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import backend.Questions;
import datatypes.QuestionCategory;
import datatypes.QuestionDatabase;

public class LoadingWithProblems {

	private JFrame frame;
	private String probemMessages;

	public LoadingWithProblems(String probemMessages) {
		this.probemMessages = probemMessages;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 290, 190);
		frame.setTitle("Error");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\millioners.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2,
				screenSize.height / 2 - frame.getSize().height / 2);
		
		JLabel lLoading = new JLabel("There was a problem");
		lLoading.setForeground(new Color(240, 255, 255));
		lLoading.setFont(new Font("Source Serif Pro", Font.PLAIN, 16));
		lLoading.setBounds(62, 11, 163, 28);
		frame.getContentPane().add(lLoading);
		
		JLabel lPleaseWait = new JLabel(probemMessages);
		lPleaseWait.setForeground(new Color(240, 255, 255));
		lPleaseWait.setFont(new Font("Source Serif Pro", Font.PLAIN, 14));
		lPleaseWait.setBounds(50, 50, 216, 20);
		frame.getContentPane().add(lPleaseWait);
		
		JButton button = new JButton("Reload App");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] c = QuestionCategory.getQuestionCategory(13);
				QuestionDatabase q = new QuestionDatabase();
				Questions.getQuestions(q, c);
				long ct = System.currentTimeMillis();
				while (System.currentTimeMillis() - ct < 4*Questions.sleepTime) {
					if (q.getQuestionModel(0) != null) {
						StartingPageView st = new StartingPageView(q, c);
						st.setVisible(true);
						frame.dispose();
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {}
				}
				lPleaseWait.setText("Reload App - Faild");
				button.setVisible(false);
			}
		});
		button.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		button.setBackground(new Color(255, 255, 255));
		button.setBounds(144, 114, 101, 28);
		frame.getContentPane().add(button);
		button.setFocusPainted(false);
		
		JButton bClose = new JButton("Reload App");
		bClose.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bClose.setFocusPainted(false);
		bClose.setBackground(Color.WHITE);
		bClose.setBounds(34, 114, 101, 28);
		frame.getContentPane().add(bClose);
		
		JLabel lBackground = new JLabel("");
		lBackground.setIcon(new ImageIcon(".\\resources\\background.png"));
		lBackground.setBounds(0, 0, 276, 153);
		frame.getContentPane().add(lBackground);	
		
		frame.setVisible(true);
	}
}
