package ru.tayrinn.telegram.coopdance.models;

public class ChatMessage {

    public final static String KEY_TEXT = "TEXT";
    public final static String KEY_MESSAGE_ID = "MESSAGE_ID";
    public final static String KEY_CHAT_ID = "CHAT_ID";
    public final static String KEY_AUTHOR_USERNAME = "AUTHOR_USERNAME";
    public final static String KEY_IS_BOT = "IS_BOT";
    public final static String KEY_PAYLOAD = "PAYLOAD";
    public final static String KEY_TIMESTAMP = "TIMESTAMP";

    private String text;
    private String messageId;
    private String chatId;
    private String authorUsername;
    private boolean isBot;
    private String payload;
    private long timestamp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public int isBot() {
        return isBot ? 1 : 0;
    }

    public void setBot(int bot) {
        isBot = bot == 1;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "text='" + text + '\'' +
                ", messageId='" + messageId + '\'' +
                ", chatId='" + chatId + '\'' +
                ", authorUsername='" + authorUsername + '\'' +
                ", isBot=" + isBot +
                ", payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
