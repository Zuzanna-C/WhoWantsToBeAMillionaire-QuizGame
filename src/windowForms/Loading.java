package windowForms;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Loading {

	private JFrame frmLoading;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading window = new Loading();
					window.frmLoading.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Loading() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoading = new JFrame();
		frmLoading.setResizable(false);
		frmLoading.setTitle("Loading");
		frmLoading.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\millioners.png"));
		frmLoading.setBounds(100, 100, 241, 122);
		frmLoading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoading.getContentPane().setLayout(null);
		
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
}
