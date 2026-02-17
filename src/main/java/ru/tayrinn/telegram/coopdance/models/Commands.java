package ru.tayrinn.telegram.coopdance.models;

import com.google.gson.Gson;

public class Commands {
    public static final String SEPARATOR = "---";
    public static final String ADD_GIRL = "1";
    public static final String ADD_BOY = "2";
    public static final String ADD_GIRL_AND_BOY = "3";
    public static final String ADD_BOY_AND_GIRL = "4";
    public static final String CANCEL = "5";
    public static final String REFRESH = "6";
    private static Gson gson = new Gson();


    public static String format(String command, String message, String messageId) {
        CallbackData callbackData = new CallbackData();
        callbackData.c = command;
        callbackData.m = message;
        if (messageId != null) {
            callbackData.i = messageId;
        }
        return gson.toJson(callbackData);
    }

    public static CallbackData parseCommand(String value) {
        return gson.fromJson(value, CallbackData.class);
    }

    public static String toJson(Object data) {
        return gson.toJson(data);
    }

}
