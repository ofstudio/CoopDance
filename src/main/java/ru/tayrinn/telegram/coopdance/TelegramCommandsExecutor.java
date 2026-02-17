package ru.tayrinn.telegram.coopdance;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface TelegramCommandsExecutor {
    void send(BotApiMethod method);

    void sendChatMessage(String chatId, String text);
    void sendAlertMessage(String chatId, String text);
}
