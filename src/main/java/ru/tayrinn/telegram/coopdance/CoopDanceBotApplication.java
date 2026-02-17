package ru.tayrinn.telegram.coopdance;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class CoopDanceBotApplication {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new BotTelegramLongPollingCommandBot(
                    getenv.get("BOT_NAME"),
                    getenv.get("BOT_TOKEN"),
                    getenv.get("DATABASE_URL")));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
