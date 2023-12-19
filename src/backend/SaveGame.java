package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import datatypes.QuestionDatabase;
import datatypes.QuestionModel;

public class SaveGame {
	
	public static void saveToProperties(String[] category, int playerScore, int whichQuestion) {
        Properties properties = new Properties();

        for (int i = 0; i < category.length; i++) {
            if (category[i] != null) {
                String questionPrefix = "category" + i + ".";
                properties.setProperty(questionPrefix + "categoryText", category[i]);
            }
        }  
        
        properties.setProperty("playerScore", Integer.toString(playerScore));
        properties.setProperty("whichQuestion", Integer.toString(whichQuestion));
        
        try (FileOutputStream output = new FileOutputStream("settings.properties")) {
            properties.store(output, null);
            System.out.println("Plik properties został pomyślnie zapisany.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public static Map<String, Object> loadFromProperties() {
		String FILE_PATH = "settings.properties";
        Properties properties = new Properties();
        QuestionDatabase questions = new QuestionDatabase();
        Map<String, Object> settings = new HashMap<>();

        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] categories = new String[13];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = properties.getProperty("category" + i + ".categoryText", "");
        }

        int playerScore = Integer.parseInt(properties.getProperty("playerScore", "0"));
        int whichQuestion = Integer.parseInt(properties.getProperty("whichQuestion", "0"));

        settings.put("categories", categories);
        settings.put("playerScore", playerScore);
        settings.put("whichQuestion", whichQuestion);

        return settings;
    }
}
