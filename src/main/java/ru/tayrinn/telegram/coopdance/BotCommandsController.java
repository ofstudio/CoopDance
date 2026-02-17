package ru.tayrinn.telegram.coopdance;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tayrinn.telegram.coopdance.handlers.ButtonsClickHandler;
import ru.tayrinn.telegram.coopdance.handlers.InlineQueryHandler;
import ru.tayrinn.telegram.coopdance.handlers.MessageQueryHandler;
import ru.tayrinn.telegram.coopdance.models.ChatDao;
import ru.tayrinn.telegram.coopdance.models.Dances;

import javax.sql.DataSource;
import java.sql.SQLException;

public class BotCommandsController {

    private final InlineKeyboardFactory keyboardFactory = new InlineKeyboardFactory();
    private final ButtonsClickHandler buttonsClickHandler;
    private final InlineQueryHandler inlineQueryHandler;
    private final MessageQueryHandler messageQueryHandler;
    private Dances dances;
    private ChatDao chatDao;

    public BotCommandsController(TelegramCommandsExecutor telegramCommandsExecutor,
                                 DataSource dataSource, String botName) {
        try {
            chatDao = new ChatDaoImpl(dataSource);
            chatDao.create();
            dances = new Dances(chatDao);
        } catch (SQLException throwables) {
            //Utils.sendException(telegramCommandsExecutor, throwables);
	    System.out.println(throwables);
        }
        messageQueryHandler = new MessageQueryHandler(telegramCommandsExecutor, keyboardFactory, chatDao, dances);
        buttonsClickHandler = new ButtonsClickHandler(telegramCommandsExecutor, keyboardFactory, dances, botName);
        inlineQueryHandler = new InlineQueryHandler(telegramCommandsExecutor, keyboardFactory);
    }

    public void handle(Update update) {
        if (update.hasInlineQuery()) {
            inlineQueryHandler.handle(update.getInlineQuery());
        } else if (update.hasCallbackQuery()) {
            buttonsClickHandler.handle(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            messageQueryHandler.handle(update.getMessage());
        }
    }
}
