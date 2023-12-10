package backend;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AudienceHelpPlot {
	
	private static void Plot(int[] values) {	
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		data.setValue(values[0], "Value", "A");
		data.setValue(values[1], "Value", "B");
		data.setValue(values[2], "Value", "C");
		data.setValue(values[3], "Value", "D");
	
		JFreeChart chart = ChartFactory.createBarChart("Wyniki publiczności", "", "", data, PlotOrientation.VERTICAL, false, true, false);		
		ChartFrame frame = new ChartFrame("Pytanie do publiczności", chart);
		frame.setBackground(Color.CYAN);
		frame.setResizable(false);
		frame.setSize(500,400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void createPlot4Answers(ArrayList<Integer> answersSet) {
		if(ThreadLocalRandom.current().nextInt(0, 101) > 95) {
			int newOne;
			do {} while ((newOne = ThreadLocalRandom.current().nextInt(0, 4)) == answersSet.indexOf(1)); 
			answersSet.set(answersSet.indexOf(1), -1);
			answersSet.set(newOne, 1);
		}
		int correctValue = ThreadLocalRandom.current().nextInt(26, 76);
		int[] incorrect = new int[3];
		
		do {
			do {} while (correctValue + (incorrect[0] = ThreadLocalRandom.current().nextInt(0, correctValue)) > 99);
			do {} while (correctValue + incorrect[0] + (incorrect[1] = ThreadLocalRandom.current().nextInt(0, correctValue)) > 100);
		} while((incorrect[2] = 100 - correctValue - incorrect[0] - incorrect[1]) >= correctValue);
		
		int[] values = new int[4];
		for (int i = 0, j =0; i < answersSet.size(); i++) {
			if (answersSet.get(i) == 1) values[i] = correctValue;
			else {
				values[i] = incorrect[j];
				j++;
			}
		}
		Plot(values);
	}
	
	public static void createPlot2Answers(ArrayList<Integer> answersSet) {
		if(ThreadLocalRandom.current().nextInt(0, 101) > 95) {
			//-1 incorrect, 1 correct, 0 none, ABCD
			int temp = answersSet.indexOf(-1);
			answersSet.set(answersSet.indexOf(1), -1);
			answersSet.set(temp, 1);
		}
		
		int correctValue = ThreadLocalRandom.current().nextInt(51, 96);
		int incorrect = 100 - correctValue;
		
		int[] values = new int[4];
		for (int i = 0; i < answersSet.size(); i++) {
			if (answersSet.get(i) == 0) values[i] = 0;
			else if (answersSet.get(i) == 1) values[i] = correctValue;
			else values[i] = incorrect;
		}
		Plot(values);
	}
}
