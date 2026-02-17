package ru.tayrinn.telegram.coopdance.handlers;

import ru.tayrinn.telegram.coopdance.InlineKeyboardFactory;
import ru.tayrinn.telegram.coopdance.TelegramCommandsExecutor;

public abstract class BotCommandsHandler<T> {
    protected final TelegramCommandsExecutor telegramCommandsExecutor;
    protected final InlineKeyboardFactory keyboardFactory;

    protected BotCommandsHandler(TelegramCommandsExecutor telegramCommandsExecutor, InlineKeyboardFactory keyboardFactory) {
        this.telegramCommandsExecutor = telegramCommandsExecutor;
        this.keyboardFactory = keyboardFactory;
    }

    abstract void handle(T data);
}
