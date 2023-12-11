package backend;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ChangingColors {

	public static void main(String[] args) {

	}
	
	public void changeLabelColorGreen (JLabel label) {
		label.setForeground(Color.GREEN);
		Font boldFont = label.getFont().deriveFont(Font.BOLD);
        label.setFont(boldFont);
	}
	
	public void changeLabelColorRed (JLabel label) {
		label.setForeground(Color.RED);
		Font boldFont = label.getFont().deriveFont(Font.BOLD);
        label.setFont(boldFont);
	}
	
	public void changeColorBtn(JButton btn) {
		btn.setBackground(Color.GREEN);
		Font boldFont = btn.getFont().deriveFont(Font.BOLD);
        btn.setFont(boldFont);
	}
	
	public void showRightAnswer(int index, JButton btns[]) {
		switch (index) {
		case 0:
			changeColorBtn(btns[index]);
			break;
		case 1:
			changeColorBtn(btns[index]);
			break;
		case 2:
			changeColorBtn(btns[index]);
			break;
		case 3:
			changeColorBtn(btns[index]);
			break;
		}
	}

}
