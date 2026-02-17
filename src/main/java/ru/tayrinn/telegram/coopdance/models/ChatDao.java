package ru.tayrinn.telegram.coopdance.models;

import java.sql.SQLException;
import java.util.List;

public interface ChatDao {

    void create() throws SQLException;

    void writeChatMessage(ChatMessage chatMessage) throws SQLException;

    void writeDance(Dance dance) throws SQLException;

    Dance getDanceByMessageId(String messageId) throws SQLException;

    List<ChatMessage> getLastChatMessages(String chatId, String authorName, Integer count) throws SQLException;

    void updateChatMessage(ChatMessage chatMessage);

}
