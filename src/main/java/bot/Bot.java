package bot;

import bot.commands.Buttons;
import bot.commands.Slash;
import bot.commands.Texts;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

import static bot.HelpMethods.*;
import static bot.finalVariables.ErrorTexts.*;
import static bot.finalVariables.ButtonsCallBackData.*;
import static bot.finalVariables.Other.*;

public class Bot extends TelegramLongPollingBot {

    Slash commandSlash = new Slash();
    Buttons commandButton = new Buttons();
    Texts commandText = new Texts();
    Map<Long, Boolean> usersId = new HashMap<>();
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> executeSender(commandSlash.slashStart(chatId, usersId));
                case "/chatId" -> executeSender(commandSlash.slashChatId(chatId));
                case "/usersId" -> executeSender(commandSlash.slashUsersId(chatId, usersId));
                default -> {

                    if (messageText.toLowerCase().contains("neyarex") && usersId.get(chatId) && chatId != neyarexChatId) {

                        executeSender(commandText.neyarexHandler(chatId, update, messageText));
                        usersId.put(chatId, false);
                        executeSender(sender(chatId, "Обращение отправлено!"));
                    } else if (!usersId.get(chatId) && chatId != neyarexChatId) {

                        executeSender(sender(chatId, "Обращение уже отправлено. " +
                                "Нельзя обратиться дважды!"));
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
                    executeEditor(commandButton.buttonBegin(chatId, messageId));
                    // отправляем сообщение с главным меню
                    executeSender(commandButton.buttonBegin2(chatId));
                }
                case MAIN_MANU -> executeEditor(commandButton.buttonMainManu(chatId, messageId));
                case WELCOME -> executeEditor(commandButton.buttonWelcome(chatId, messageId));
                case BANKS -> executeEditor(commandButton.buttonBanks(chatId, messageId));
                case T_BANK -> executeEditor(commandButton.buttonTBank(chatId, messageId));
                case ALFA_BANK -> executeEditor(commandButton.buttonAlfaBank(chatId, messageId));
                case WORKS -> executeEditor(commandButton.buttonWorks(chatId, messageId));
                default -> {
                    try {
                        long userChatId = Long.parseLong(callBackData);
                        usersId.put(userChatId, true);
                        executeEditor(editor(chatId, STR."Пользователь снова может обратиться к вам! \{userChatId}", messageId));
                        executeSender(sender(userChatId, "Ваше обращение обработано! Вы можете обратиться снова!"));
                    } catch (Exception _) {
                    }
                }
            }
        }
    }

    public void unknownMessage(long chatId) {

        SendMessage message = sender(chatId, UNKNOWN_MESSAGE);
        executeSender(message);
    }

    public void executeSender(SendMessage message) {

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println((ERROR_TEXT + e.getMessage()));
        }
    }

    public void executeEditor(EditMessageText message) {

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
        return "token";
    }
}