package chatgpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import datatypes.QuestionAndAnswers;
import datatypes.Questions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatGPT {
	
	private static Logger logger = LogManager.getLogger(ChatGPT.class.getName());
	private static final String chatgpt_url = "https://api.openai.com/v1/chat/completions";
	private static final String apiKey = "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";
	private static final String chatgpt_model = "gpt-3.5-turbo";	
	
	public static Questions getQuestions() throws IOException, URISyntaxException {
		
		String request = "Wygeneruj 12 pytań do teleturnieju Milionerzy każde kolejne pytanie powinno być na poziomie trudniejsze średnim i dotyczyć wiedzy ogólnej. Do każdego pytania wygeneruj krótką maks 5 słów poprawną odpowiedź i 3 też maks 5 słów błędne odpowiedzi. "
				+ "Danie przedstaw w podanej formie: " + "Pytanie_1: treść " + "Odp_A: treść " + "Odp_B: treść "
				+ "Odp_C: treść " + "Odp_D: treść " + "Odp_A ma zawierać poprawną odpowiedź na pytanie. "
				+ "Pomiędzy kolejnymi pytaniami dawaj enter. "
				+ "Wygeneruj tylko pytania i odpowiedzi bez dodatkowych komentarzy lub uwag.";
		
		try {
			URL url;
			try {
				url = (new URI(chatgpt_url)).toURL();
			} catch (URISyntaxException e) {
				logger.error(e);
				throw e;
			}
			logger.info("url - succes.");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + apiKey);
			connection.setRequestProperty("Content-Type", "application/json");

			//The request body
			String body = "{\"model\": \"" + chatgpt_model + "\", \"messages\": [{\"role\": \"user\", \"content\": \""
					+ request + "\"}]}";
			
			connection.setDoOutput(true);
			logger.info("connection - succes.");
			
			try(OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
				writer.write(body);
				logger.info("writer - succes.");
			}
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				StringBuffer response = new StringBuffer();
		
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				
				logger.info("reader - succes.");
				//calls the method to extract the questions.
				return extractQuestionsFromJSONResponse(response.toString());
			}

		} catch (IOException e) {
			logger.error(e);
			throw e;
		}
	}

	private static Questions extractQuestionsFromJSONResponse(String response) {
		Questions questions = new Questions();
		for(int i = 1; i <= 12; i++) {
			String[] answers = new String[4];			
			String question = response.substring(response.indexOf("Pytanie_" + i + ": ") + ("Pytanie_" + i + ": ").length(), response.indexOf("Odp_A", response.indexOf("Pytanie_" + i + ": ") + ("Pytanie_" + i + ": ").length()));
			question = question.substring(0,question.length()-2).replace("\\", "");
			
			answers[0] = response.substring(response.indexOf("Odp_A: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_A: ".length(), response.indexOf("Odp_B: ", response.indexOf("Odp_A: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_A: ".length()) - 2).replace("\\", ""); // 2 because \n
			answers[1] = response.substring(response.indexOf("Odp_B: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_B: ".length(), response.indexOf("Odp_C: ", response.indexOf("Odp_B: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_B: ".length()) - 2).replace("\\", "");
			answers[2] = response.substring(response.indexOf("Odp_C: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_C: ".length(), response.indexOf("Odp_D: ", response.indexOf("Odp_C: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_C: ".length()) - 2).replace("\\", "");
			if (i<12) answers[3] = response.substring(response.indexOf("Odp_D: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_D: ".length(), response.indexOf("Pytanie_" + (i+1) + ": ", response.indexOf("Odp_D: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_D: ".length()) - 4).replace("\\", ""); // because \n\n
			else answers[3] = response.substring(response.indexOf("Odp_D: ", response.indexOf("Pytanie_" + i + ": ")) + "Odp_D: ".length(), response.indexOf("finish_reason", response.indexOf("Odp_D: ") + "Odp_D: ".length()) - 16).replace("\\", ""); // because "      },      "
			
			questions.addQuestionAndAnswers(new QuestionAndAnswers(question, answers));
			logger.info("Added Question " + i);
		}
		
		logger.info("extractMessageFromJSONResponse - succes.");
		return questions;
	}

}