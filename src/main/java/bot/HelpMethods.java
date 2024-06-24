package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class HelpMethods {

    public static SendMessage sender(long chatId, String text) {
        SendMessage message = new SendMessage();

//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("Hello world!");
//        keyboardRows.add(row);
//        keyboardMarkup.setKeyboard(keyboardRows);
//        message.setReplyMarkup(keyboardMarkup);

        message.enableHtml(true);
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

    public static EditMessageText editor(long chatId, String text, long messageId) {
        EditMessageText message = new EditMessageText();
        message.enableHtml(true);
        message.setChatId(chatId);
        message.setText(text);
        message.setMessageId((int) messageId);
        return message;
    }

    public static InlineKeyboardButton newButton(String name, String callBackData) {
        var button = new InlineKeyboardButton();
        button.setText(name);
        button.setCallbackData(callBackData);
        return button;
    }

    public static InlineKeyboardButton newButtonWithUrl(String name, String callBackData, String url) {
        var button = new InlineKeyboardButton();
        button.setText(name);
        button.setUrl(url);
        button.setCallbackData(callBackData);
        return button;
    }

    public static InlineKeyboardMarkup newMarkup1(List<InlineKeyboardButton> rowButtons) {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
        rowsButtons.add(rowButtons);
        markup.setKeyboard(rowsButtons);

        return markup;
    }

    public static InlineKeyboardMarkup newMarkup2(List<InlineKeyboardButton> rowButtons1,
                                                  List<InlineKeyboardButton> rowButtons2) {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
        rowsButtons.add(rowButtons1);
        rowsButtons.add(rowButtons2);
        markup.setKeyboard(rowsButtons);

        return markup;
    }

    public static InlineKeyboardMarkup newMarkup3(List<InlineKeyboardButton> rowButtons1,
                                                  List<InlineKeyboardButton> rowButtons2,
                                                  List<InlineKeyboardButton> rowButtons3) {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
        rowsButtons.add(rowButtons1);
        rowsButtons.add(rowButtons2);
        rowsButtons.add(rowButtons3);
        markup.setKeyboard(rowsButtons);

        return markup;
    }
}