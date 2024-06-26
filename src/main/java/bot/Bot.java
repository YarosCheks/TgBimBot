package bot;

import bot.commands.Buttons;
import bot.commands.Slash;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bot.HelpMethods.*;
import static bot.finalVariables.ErrorTexts.*;
import static bot.finalVariables.ButtonsCallBackData.*;
import static bot.finalVariables.Other.*;

public class Bot extends TelegramLongPollingBot {

    Slash commandSlash = new Slash();
    Buttons commandButton = new Buttons();
    Map<Long, Boolean> usersId = new HashMap<>();
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> {
                    usersId.put(chatId, true);
                    executeMessage1(commandSlash.slashStart(chatId));
                }
                case "/chatId" -> executeMessage1(commandSlash.slashChatId(chatId));
                case "/usersId" -> executeMessage1(sender(chatId, STR."Список пользователей: \{usersId}"));
                default -> {

                    if (messageText.toLowerCase().contains("neyarex") && usersId.get(chatId) && chatId != neyarexChatId) {

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

                        executeMessage1(message);
                        usersId.put(chatId, false);
                        executeMessage1(sender(chatId, "Обращение отправлено!"));
                    } else if (!usersId.get(chatId) && chatId != neyarexChatId) {
                        executeMessage1(sender(chatId, "Обращение уже отправлено. Нельзя обратиться дважды!"));
                    } else {
                        unknownMessage(chatId);
                    }
                }
            }

        } else if (update.hasCallbackQuery()) {

            String callBackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();

            switch (callBackData) {
                case BEGIN -> {
                    // убираем кнопку под стартовым сообщением
                    executeMessage2(commandButton.buttonBegin(chatId, messageId));
                    // отправляем сообщение с главным меню
                    executeMessage1(commandButton.buttonBegin2(chatId));
                }
                case MAIN_MANU -> executeMessage2(commandButton.buttonMainManu(chatId, messageId));
                case WELCOME -> executeMessage2(commandButton.buttonWelcome(chatId, messageId));
                case BANKS -> executeMessage2(commandButton.buttonBanks(chatId, messageId));
                case T_BANK -> executeMessage2(commandButton.buttonTBank(chatId, messageId));
                case ALFA_BANK -> executeMessage2(commandButton.buttonAlfaBank(chatId, messageId));
                case WORKS -> executeMessage2(commandButton.buttonWorks(chatId, messageId));
                default -> {
                    try {
                        usersId.put(Long.parseLong(callBackData), true);
                        executeMessage2(editor(chatId, "Пользователь снова может обратиться к вам!", messageId));
                    } catch (Exception _) {
                    }
                }
            }
        }
    }

    public void unknownMessage(long chatId) {

        SendMessage message = sender(chatId, UNKNOWN_MESSAGE);
        executeMessage1(message);
    }

    public void executeMessage1(SendMessage message) {

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println((ERROR_TEXT + e.getMessage()));
        }
    }

    public void executeMessage2(EditMessageText message) {

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println((ERROR_TEXT + e.getMessage()));
        }
    }


    @Override
    public String getBotUsername() {
        return "TgBimBot";
    }

    @Override
    public String getBotToken() {
        return "7464725558:AAHkGrUuqqSoAbyMXWNVbqwTPkQd_2cmUWE";
    }
}