package windowForms;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoadingWithProblems {

	private JFrame frame;
	private String[] probemMessages= {"There is no internet connection.","hHat GPT is out of service"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingWithProblems window = new LoadingWithProblems();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoadingWithProblems() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 190);
		frame.setTitle("Error");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\millioners.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lLoading = new JLabel("There was a problem");
		lLoading.setForeground(new Color(240, 255, 255));
		lLoading.setFont(new Font("Source Serif Pro", Font.PLAIN, 16));
		lLoading.setBounds(62, 11, 163, 28);
		frame.getContentPane().add(lLoading);
		
		JButton button = new JButton("Reload");
		button.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		button.setBackground(new Color(255, 255, 255));
		button.setBounds(93, 91, 101, 28);
		frame.getContentPane().add(button);
		button.setFocusPainted(false);
		
		JLabel lPleaseWait = new JLabel(probemMessages[0]);
		lPleaseWait.setForeground(new Color(240, 255, 255));
		lPleaseWait.setFont(new Font("Source Serif Pro", Font.PLAIN, 14));
		lPleaseWait.setBounds(50, 50, 216, 20);
		frame.getContentPane().add(lPleaseWait);
		
		JLabel lBackground = new JLabel("");
		lBackground.setIcon(new ImageIcon(".\\resources\\background.png"));
		lBackground.setBounds(0, 0, 276, 153);
		frame.getContentPane().add(lBackground);
		
		
		
		
	}
}
