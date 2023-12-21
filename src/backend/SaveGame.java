package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datatypes.QuestionDatabase;
import datatypes.QuestionModel;

public class SaveGame {
	private static Logger logger = LogManager.getLogger(SaveGame.class.getName());
	
	public static void saveToProperties(String[] category, int playerScore, int whichQuestion, int publicty,
			int fiftyFifty, int change) {
        Properties properties = new Properties();

        for (int i = 0; i < category.length; i++) {
            if (category[i] != null) {
                String questionPrefix = "category" + i + ".";
                properties.setProperty(questionPrefix + "categoryText", category[i]);
            }
        }  
        
        properties.setProperty("playerScore", Integer.toString(playerScore));
        properties.setProperty("whichQuestion", Integer.toString(whichQuestion));
        properties.setProperty("publicityFlag", Integer.toString(publicty));
        properties.setProperty("fiftyFiftyFlag", Integer.toString(fiftyFifty));
        properties.setProperty("changeFlag", Integer.toString(change));
        
        try (FileOutputStream output = new FileOutputStream("settings.properties")) {
            properties.store(output, null);
            System.out.println("Plik properties został pomyślnie zapisany.");
        } catch (IOException ex) {
        	logger.error("Error occurred while saving to properties file", ex);
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
        	logger.error("Error occurred while loading properties file", e);
            e.printStackTrace();
        }

        String[] categories = new String[13];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = properties.getProperty("category" + i + ".categoryText", "");
        }

        try {
            int playerScore = Integer.parseInt(properties.getProperty("playerScore", "0"));
            int whichQuestion = Integer.parseInt(properties.getProperty("whichQuestion", "0"));
            int publicityFlag = Integer.parseInt(properties.getProperty("publicityFlag", "0"));
            int changeFlag = Integer.parseInt(properties.getProperty("fiftyFiftyFlag", "0"));
            int fiftyFiftyFlag = Integer.parseInt(properties.getProperty("changeFlag", "0"));

            settings.put("categories", categories);
            settings.put("playerScore", playerScore);
            settings.put("whichQuestion", whichQuestion);
            settings.put("publicityFlag", publicityFlag);
            settings.put("fiftyFiftyFlag", fiftyFiftyFlag);
            settings.put("changeFlag", changeFlag);
        } catch (NumberFormatException ex) {
            logger.error("Error occurred while parsing properties to integers", ex);
            ex.printStackTrace();
        }

        return settings;
    }
}
