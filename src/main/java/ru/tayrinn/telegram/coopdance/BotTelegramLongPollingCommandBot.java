package ru.tayrinn.telegram.coopdance;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotTelegramLongPollingCommandBot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    public BotTelegramLongPollingCommandBot(String botName, String botToken) {
        BOT_NAME = botName;
        BOT_TOKEN = botToken;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = getUserName(msg);

//        if (update.hasInlineQuery()) {
            handleInlineQuery(update.getInlineQuery(), chatId);
//            return;
//        }
//        setAnswer(chatId, userName, "Hello world!!1!!");
    }

    /**
     * Формирование имени пользователя
     * @param msg сообщение
     */
    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    private void handleInlineQuery (InlineQuery inlineQuery, Long chatId) {
        try {
            execute(new BotInlineQuery(inlineQuery).answer(chatId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправка ответа
     * @param chatId id чата
     * @param userName имя пользователя
     * @param text текст ответа
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}