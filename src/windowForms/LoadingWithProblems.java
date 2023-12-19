package windowForms;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import backend.Questions;
import backend.SaveGame;
import datatypes.QuestionCategory;
import datatypes.QuestionDatabase;

public class LoadingWithProblems {

	private JFrame frame;
	private String probemMessages;
	private Object[] data;
	private boolean reloadF = false;

	public LoadingWithProblems(String probemMessages, Object[] data) {
		this.probemMessages = probemMessages;
		this.data = data;
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
		
		JButton bSave = new JButton("Save ypur progres");
		bSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SaveGame.saveToProperties((String[]) data[0], (int) data[1], (int) data[2], (int) data[3], (int) data[4], (int) data[5]);
					lPleaseWait.setText("Your progress - saved");
					bSave.setEnabled(false);
				} catch (Exception e2) {
					lPleaseWait.setText("Cannot save ended Game");
					bSave.setEnabled(false);
				}
			}
		});
		bSave.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		bSave.setFocusPainted(false);
		bSave.setBackground(Color.WHITE);
		bSave.setBounds(34, 114, 101, 28);
		frame.getContentPane().add(bSave);
		
		JButton ReloadClose = new JButton("Reload App");
		ReloadClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reloadF) System.exit(0);
				new Thread(() -> {
					lPleaseWait.setText("Loading");
					ReloadClose.setEnabled(false);
					String[] c = QuestionCategory.getQuestionCategory(13);
					QuestionDatabase q = new QuestionDatabase();
					ExecutorService exeSerPool = Executors.newFixedThreadPool(1);
					Questions.getQuestions(q, c, exeSerPool);
					exeSerPool.shutdown();
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
					ReloadClose.setEnabled(true);
					ReloadClose.setText("Close");
					reloadF = true;
				}).start();
			}
		});
		ReloadClose.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		ReloadClose.setBackground(new Color(255, 255, 255));
		ReloadClose.setBounds(144, 114, 101, 28);
		frame.getContentPane().add(ReloadClose);
		ReloadClose.setFocusPainted(false);
		
		JLabel lBackground = new JLabel("");
		lBackground.setIcon(new ImageIcon(".\\resources\\background.png"));
		lBackground.setBounds(0, 0, 276, 153);
		frame.getContentPane().add(lBackground);	
		
		frame.setVisible(true);
	}
}
