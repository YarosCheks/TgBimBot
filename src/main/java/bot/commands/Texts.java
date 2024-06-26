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

    public SendMessage neyarexHandler(long chatId, Update update, String messageText) {

        SendMessage message = sender(neyarexChatId,
                STR."Пидор @\{update.getMessage().getChat().getUserName()} " +
                        STR."обратился к вам:\n\n\{messageText}\n\nChatId: \{chatId}");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        rowButtons1.add(newButton("Обработано", Long.toString(chatId)));
        rowsButtons.add(rowButtons1);
        markup.setKeyboard(rowsButtons);
        message.setReplyMarkup(markup);

        return message;
    }
}
