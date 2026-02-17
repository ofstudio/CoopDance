package ru.tayrinn.telegram.coopdance.models;

import org.apache.commons.text.StringEscapeUtils;
import org.telegram.telegrambots.meta.api.objects.User;

public class Dancer {

    @Sex
    public String sex;
    public String ashNumber;
    public String stubName;
    public User user;

    @Override
    public String toString() {
        if (user == null) {
            return stubName;
        }
        String lastName = "";
        if (user.getLastName() != null) {
            lastName = user.getLastName() + " ";
        }
        String name = "";
        if (user.getFirstName() != null) {
            name = user.getFirstName();
        } else if (user.getLastName() == null) {
            name = user.getUserName() == null ? user.getId().toString() : user.getUserName();
        }
        return "<a href=\"tg://user?id=" + user.getId()+ "\">" + StringEscapeUtils.escapeHtml4(lastName) + StringEscapeUtils.escapeHtml4(name) + "</a>";
    }

    public boolean isUser(User another) {
        return user != null && user.getId().equals(another.getId());
    }

    public @interface Sex {
        String GIRL = "girl";
        String BOY = "boy";
    }
}
