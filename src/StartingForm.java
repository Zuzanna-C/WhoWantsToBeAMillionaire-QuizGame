import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class StartingForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartingForm window = new StartingForm();
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
	public StartingForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Zuzia\\Pictures\\Screenshots\\millioners.png"));
		frame.setBounds(100, 100, 402, 214);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2);
		
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
				NewGame newGame = new NewGame();
				newGame.setVisible(true);
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Zuzia\\Downloads\\background.jpg"));
		lblNewLabel.setBounds(0, 0, 388, 177);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
