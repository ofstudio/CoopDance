package ru.tayrinn.telegram.coopdance.handlers;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.tayrinn.telegram.coopdance.InlineKeyboardFactory;
import ru.tayrinn.telegram.coopdance.TelegramCommandsExecutor;
import ru.tayrinn.telegram.coopdance.Utils;
import ru.tayrinn.telegram.coopdance.models.CallbackData;
import ru.tayrinn.telegram.coopdance.models.Commands;
import ru.tayrinn.telegram.coopdance.models.Dance;
import ru.tayrinn.telegram.coopdance.models.Dances;

import java.sql.SQLException;

/**
 * Класс для обработки коллбеков - событий при нажатии на кнопки бота
 */
public class ButtonsClickHandler extends BotCommandsHandler<CallbackQuery> {

    private final Dances dances;
    private String botName;
    private CallbackData callbackData; // данные, которые зашиваются в кнопку при создании
    private CallbackQuery callbackQuery;
    private String messageId;

    public ButtonsClickHandler(TelegramCommandsExecutor telegramCommandsExecutor, InlineKeyboardFactory keyboardFactory, Dances dances, String botName) {
        super(telegramCommandsExecutor, keyboardFactory);
        this.dances = dances;
        this.botName = botName;
    }

    @Override
    public void handle(CallbackQuery data) {
        try {
            callbackQuery = data;
            String callData = data.getData();
            messageId = data.getInlineMessageId();
            callbackData = Commands.parseCommand(callData);
            callbackData.i = messageId;
            parseCommand();
        } catch (Exception e) {
            Utils.sendException(telegramCommandsExecutor, callbackQuery.getId(), e);
        }
    }

    private void parseCommand() {
        Dance dance = null;
        try {
            dance = dances.getDance(callbackData.m, messageId);
        } catch (Exception throwables) {
            Utils.sendException(telegramCommandsExecutor, callbackQuery.getId(), throwables);
            return;
        }
        if (dance == null) {
            telegramCommandsExecutor.sendAlertMessage(callbackQuery.getId(), "dance=null");
            return;
        }
        User user = callbackQuery.getFrom();
        switch (callbackData.c) {
            case Commands.ADD_GIRL_AND_BOY:
            case Commands.ADD_BOY_AND_GIRL:
                String utf = callbackData.c + Commands.SEPARATOR + callbackData.i;
                dance.findSingleDancerAndRemove(user);
                if (dance.hasDancer(user)) {
                    telegramCommandsExecutor.sendAlertMessage(callbackQuery.getId(), "Вы уже записаны");
                }
                sendInlineAnswer(utf);
                break;
            case Commands.ADD_GIRL :
            case Commands.ADD_BOY :
                if (dance.hasDancer(user)) {
                    telegramCommandsExecutor.sendAlertMessage(callbackQuery.getId(), "Вы уже записаны");
                    return;
                }
                dance.processCommand(callbackData.c, user);
                addDanceAndEditMessage(dance);
                break;
            case Commands.CANCEL:
                if (!dance.findSingleDancerAndRemove(user)) {
                    dance.findPairAndRemoveDancer(user);
                }
                telegramCommandsExecutor.sendAlertMessage(callbackQuery.getId(), "Вы не пойдёте");
                addDanceAndEditMessage(dance);
                break;
            case Commands.REFRESH:
                addDanceAndEditMessage(dance);
                break;
        }
        try {
            dances.writeDance(dance);
        } catch (SQLException throwables) {
            Utils.sendException(telegramCommandsExecutor, callbackQuery.getId(), throwables);
        }
    }

    private void addDanceAndEditMessage(Dance dance) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setInlineMessageId(messageId);
        newMessage.setReplyMarkup(keyboardFactory.createDanceKeyboard(null, messageId));
        newMessage.setParseMode(ParseMode.HTML);
        newMessage.setText(dance.toString());

        telegramCommandsExecutor.send(newMessage);
    }

    private void sendInlineAnswer(String command) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setUrl("t.me/" + this.botName + "?start=" + command);

        telegramCommandsExecutor.send(answerCallbackQuery);
    }
}
