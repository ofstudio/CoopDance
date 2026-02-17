package ru.tayrinn.telegram.coopdance.models;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatMessageRowMapper implements RowMapper<ChatMessage> {
    @Override
    public ChatMessage mapRow(ResultSet resultSet, int i) throws SQLException {
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setText(resultSet.getString(ChatMessage.KEY_TEXT));
//        chatMessage.setMessageId(resultSet.getString(ChatMessage.KEY_MESSAGE_ID));
//        chatMessage.setChatId(resultSet.getString(ChatMessage.KEY_CHAT_ID));
//        chatMessage.setAuthorUsername(resultSet.getString(ChatMessage.KEY_AUTHOR_USERNAME));
//        chatMessage.setBot(resultSet.getBoolean(ChatMessage.KEY_IS_BOT));
//        chatMessage.setPayload(resultSet.getString(ChatMessage.KEY_PAYLOAD));
//        chatMessage.setTimestamp(resultSet.getLong(ChatMessage.KEY_TIMESTAMP));
        return null;
    }
}
