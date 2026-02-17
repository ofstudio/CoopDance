package ru.tayrinn.telegram.coopdance;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Utils {

    public static String convertToUtf8String(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decodeUtf8(String string) {
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendException(TelegramCommandsExecutor executor, String chatId, Throwable throwables) {
        Writer buffer = new StringWriter();
        PrintWriter pw = new PrintWriter(buffer);
        throwables.printStackTrace(pw);
        executor.sendAlertMessage(chatId, "exception=" + buffer.toString());
    }
}
