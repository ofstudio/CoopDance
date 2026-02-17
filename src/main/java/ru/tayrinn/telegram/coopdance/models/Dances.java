package ru.tayrinn.telegram.coopdance.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dances {
    private List<Dance> danceList = new ArrayList<>();
    private final ChatDao chatDao;

    public Dances(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public Dance getDance(String message, String messageId) throws SQLException {
        Dance dance = chatDao.getDanceByMessageId(messageId);
        if (dance == null) {
            dance = new Dance(message, messageId);
            chatDao.writeDance(dance);
        }
        return dance;
//        AtomicReference<Dance> result = new AtomicReference<>();
//        danceList.forEach(dance -> {
//            if (dance.messageId.equals(messageId)) {
//                result.set(dance);
//            }
//        });
//        if (result.get() == null) {
//            Dance dance = new Dance(message, messageId);
//            danceList.add(dance);
//            return dance;
//        } else {
//            return result.get();
//        }
    }

    public void writeDance(Dance dance) throws SQLException {
        chatDao.writeDance(dance);
    }

    public Dance getDanceByMessageId(String messageId) throws SQLException {
        return getDance("Коллективка", messageId);
    }
}
