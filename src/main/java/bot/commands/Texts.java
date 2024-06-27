package bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static bot.HelpMethods.newButton;
import static bot.HelpMethods.sender;
import static bot.finalVariables.Other.*;

public class Texts {

    public SendMessage supportHandler(long chatId, Update update, String messageText) {

        String username = update.getMessage().getChat().getUserName();

        SendMessage message = sender(neyarexChatId, STR."<b>Отправитель:</b> \{username}\n" +
                STR."<b>ID отправителя:</b> \{chatId}\n\n" +
                STR."<b>Переданное сооющение:</b> \{messageText}");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        rowButtons1.add(newButton("Обработано", STR."\{chatId} \{username} \{messageText}"));
        rowsButtons.add(rowButtons1);
        markup.setKeyboard(rowsButtons);
        message.setReplyMarkup(markup);

        return message;
    }
}
