package bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static bot.HelpMethods.*;
import static bot.finalVariables.ButtonsCallBackData.*;
import static bot.finalVariables.ButtonNames.*;
import static bot.finalVariables.MessageTexts.*;

public class Slash {

    public SendMessage slashStart(long chatId) {

        SendMessage message = sender(chatId, START_MESSAGE);

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();

        rowButtons1.add(newButton(BEGIN_NAME, BEGIN));

        InlineKeyboardMarkup markup = newMarkup1(rowButtons1);

        message.setReplyMarkup(markup);

        return message;
    }

    public SendMessage slashChatId(long chatId) {

        return sender(chatId, STR."ID вашего чата: \{chatId}");
    }
}
