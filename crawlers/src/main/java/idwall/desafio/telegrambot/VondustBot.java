package idwall.desafio.telegrambot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import idwall.desafio.RedditCrawler;

public class VondustBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		return "VondustBot";
	}

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			String messageReceived = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			if (messageReceived.startsWith("/NadaPraFazer ") && messageReceived.length() > 14) {

				executeSendMessage(chatId, "Compilando informações...");
				System.out.println("ChatID: " + chatId);

				String subreddits = messageReceived.substring(14);
				String[] subredditItems = subreddits.split(";");
				List<String> listMessages = new ArrayList<>();

				for (String subreddit : subredditItems) {
					executeSendMessage(chatId, "\n...Carregando informações de  " + subreddit + "\n");
					listMessages = new RedditCrawler().executeCrawling(subreddit);

					for (String messageItem : listMessages) {
						executeSendMessage(chatId, (messageItem.length() > 0) ? messageItem
								: "Falha ao processar dados do Crawler para '" + subreddit + "'");
					}
				}
			} else if (messageReceived.startsWith("/NadaPraFazer ") && messageReceived.length() < 14) {
				executeSendMessage(chatId, "Comando '/NadaPraFazer' deve conter um subreddit para buscar informações");
				System.out.println("ChatID: " + chatId);
			} else if (!messageReceived.startsWith("/NadaPraFazer ")) {
				executeSendMessage(chatId,
						"Comando '/NadaPraFazer [Lista de subrredits]' retorna informações dos subreddits");
				System.out.println("ChatID: " + chatId);
			}

		}
	}

	public void executeSendMessage(long chatId, String message) {
		SendMessage messageAguarde = new SendMessage() // Create a message object
				.setChatId(chatId).setText(message);
		try {
			execute(messageAguarde); // Sending our message object to user
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		return "481221068:AAEqO-wu1SmN56V51gDlc3HaDMKt5e_lpjo";
	}

}
