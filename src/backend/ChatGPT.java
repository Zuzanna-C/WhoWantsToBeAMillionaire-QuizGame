package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import datatypes.QuestionModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatGPT {

	private static Logger logger = LogManager.getLogger(ChatGPT.class.getName());
	private static final String chatgpt_url = "https://api.openai.com/v1/chat/completions";
	private static final String apiKey = "apikey";
	private static final String chatgpt_model = "gpt-3.5-turbo";

	public static QuestionModel getQuestion(String category) throws Exception {
		
		String request = "Wygeneruj pytanie do teleturnieju Milionerzy. "
				+ "Pytanie powinno być trudne i dotyczyć wiedzy z kategorii " + category + ". "
				+ "Długość pytania nie powinna przekraczać 8 słów."
				+ "Do pytania wygeneruj krótką maks 3 słów poprawną odpowiedź i 3 też maks 3 słów błędne odpowiedzi. "
				+ "Danie przedstaw w podanej formie: Question: treść_pytania nowa linia Answer_A: treść_odpA nowa linia Answer_B: treść_odpB nowa linia Answer_C: treść_odpC nowa lina Answer_D: treść_odpD. "
				+ "Odp_A ma zawierać poprawną odpowiedź na pytanie. "
				+ "Dodatkowo tekst pytania i odpowiedzi podaj w języku angielskim"
				+ "Wygeneruj tylko pytanie i odpowiedzi bez dodatkowych komentarzy lub uwag.";
		
		try {
			URL url;
			try {
				url = (new URI(chatgpt_url)).toURL();
			} catch (URISyntaxException e) {
				logger.error("Incorrect URI.");
				logger.error(e);
				throw new Exception("ChatGPT getQuestion");
			} catch (MalformedURLException e) {
				logger.error("URL from URI is not correctly formatted or contains invalid characters.");
				logger.error(e);
				throw new Exception("ChatGPT getQuestion");
			}
			logger.info("URL - success.");

			// Wskazanie warości np. useragent
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + apiKey);
			connection.setRequestProperty("Content-Type", "application/json");

			String body = "{\"model\": \"" + chatgpt_model + "\", \"messages\": [{\"role\": \"user\", \"content\": \""
					+ request + "\"}]}";
			
			connection.setDoOutput(true);
			logger.info("Connection - success.");
			
			try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {writer.write(body);}
			logger.info("Writer - success.");
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				StringBuffer response = new StringBuffer();
		
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				
				logger.info("Reader - success.");
				return extractQuestionModelFromJSONResponse(response.toString());
			}

		} catch (IOException e) {
			if (e.getMessage().hashCode() == "api.openai.com".hashCode() || e.getMessage().hashCode() == "No route to host: connect".hashCode()) {
				logger.error("No internet connection.");
				throw new Exception("No internet connection");
			}
			// 2023-12-18 00:10:29,836 ERROR backend.ChatGPT.getQuestion | java.net.NoRouteToHostException: No route to host: connect
			else if (e.getMessage().hashCode() == "Server returned HTTP response code: 401 for URL: https://api.openai.com/v1/chat/completions".hashCode()) logger.error("Apikey is not correct."); 
			else if (e.getMessage().hashCode() == "Server returned HTTP response code: 429 for URL: https://api.openai.com/v1/chat/completions".hashCode()) logger.error("Reached requests limits.");
			else if (e.getMessage().hashCode() == "Server returned HTTP response code: 500 for URL: https://api.openai.com/v1/chat/completions".hashCode()) logger.error("The openai server had an error while processing request.");
			else if (e.getMessage().hashCode() == "Server returned HTTP response code: 503 for URL: https://api.openai.com/v1/chat/completions".hashCode()) logger.error("The openai engine is currently overloaded.");
			logger.error(e);
			throw new Exception("ChatGPT getQuestion");
		}
	}

	private static QuestionModel extractQuestionModelFromJSONResponse(String response) throws Exception {
	    String[] answers = new String[4];
	    String questionText = null;
	    try {
	        if (response.indexOf("new line") != -1)
	            throw new IndexOutOfBoundsException();
	        questionText = response.substring(response.indexOf("Question: ") + ("Question: ").length(),
	                response.indexOf("Answer_A", response.indexOf("Question: ") + ("Question: ").length()));
	        questionText = questionText.substring(0, questionText.length() - 2).replace("\\", "");

	        answers[0] = response
	                .substring(response.indexOf("Answer_A: ") + "Answer_A: ".length(),
	                        response.indexOf("Answer_B: ", response.indexOf("Answer_A: ") + "Answer_A: ".length()) - 2)
	                .replace("\\", ""); // 2 because \n
	        answers[1] = response
	                .substring(response.indexOf("Answer_B: ") + "Answer_B: ".length(),
	                        response.indexOf("Answer_C: ", response.indexOf("Answer_B: ") + "Answer_B: ".length()) - 2)
	                .replace("\\", "");
	        answers[2] = response
	                .substring(response.indexOf("Answer_C: ") + "Answer_C: ".length(),
	                        response.indexOf("Answer_D: ", response.indexOf("Answer_C: ") + "Answer_C: ".length()) - 2)
	                .replace("\\", "");
	        answers[3] = response
	                .substring(response.indexOf("Answer_D: ") + "Answer_D: ".length(),
	                        response.indexOf("\"      },", response.indexOf("Answer_D: ") + "Answer_D: ".length()))
	                .replace("\\", "");
	    } catch (IndexOutOfBoundsException e) {
	        logger.error("ChatGPT response incorrect format.");
	        logger.error(e);
	        throw new Exception("ChatGPT getQuestion");
	    }
	    logger.info("Success.");
	    return (new QuestionModel(questionText, answers)).shuffleAnswerOptions();
	}

	
}