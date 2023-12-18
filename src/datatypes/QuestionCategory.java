package datatypes;

import java.util.concurrent.ThreadLocalRandom;

public class QuestionCategory {
	
	private static final String[] questionCategory = {
            "Literatura światowa",
            "Historia Polski",
            "Matematyka",
            "Geografia",
            "Nauki przyrodnicze",
            "Sztuka i kultura",
            "Sport",
            "Muzyka",
            "Film",
            "Technologia",
            "Polityka",
            "Astronomia",
            "Kuchnia świata",
            "Informatyka",
            "Mitologia",
            "Języki obce",
            "Zdrowie i medycyna",
            "Psychologia",
            "Biznes i ekonomia",
            "Religia",
            "Sławni ludzie",
            "Gry komputerowe",
            "Transport",
            "Architektura",
            "Moda i styl",
            "Hobby",
            "Zwierzęta",
            "Gry planszowe",
            "E-sport",
            "Przyroda"
        };
	
	public static String[] getQuestionCategory(int n) {
		int qCounter = 0;
		String[] qCategory = new String[n];
		do {
			int index = ThreadLocalRandom.current().nextInt(0, 29);
			qCategory[qCounter] = questionCategory[index];
			qCounter++;
		} while (qCounter < n);
		return qCategory;
	}
}
