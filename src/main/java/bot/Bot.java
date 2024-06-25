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
import java.util.List;

import static bot.HelpMethods.*;
import static bot.finalVariables.ButtonNames.*;
import static bot.finalVariables.ErrorTexts.*;
import static bot.finalVariables.ButtonsCallBackData.*;
import static bot.finalVariables.Links.*;
import static bot.finalVariables.MessageTexts.*;

public class Bot extends TelegramLongPollingBot {

    Slash commandSlash = new Slash();
    Buttons commandButton = new Buttons();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
//            long nayarekChatId = 6422071424L;
            long neyarexChatId = 7108821205L;

            switch (messageText) {
                case "/start" -> executeMessage1(commandSlash.slashStart(chatId));
                case "/chatId" -> executeMessage1(sender(chatId, STR."Ваш chat ID: \{chatId}"));
                case "/oslota" -> executeMessage1(sender(neyarexChatId,
                            STR."Пидор @\{update.getMessage().getChat().getUserName()} написал /oslota"));

                case "/myName" -> executeMessage1(sender(chatId, STR."@\{update.getMessage().getChat().getUserName()}"));
                default -> {
                    if (messageText.contains("neyarex")) {
                        executeMessage1(sender(neyarexChatId,
                                STR."Пидор @\{update.getMessage().getChat().getUserName()} обратился к вам:\n\n" + messageText + "\n\nChatId: " + chatId));
                        executeMessage1(sender(chatId, "Обращение принято!"));
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
                    SendMessage message = sender(chatId, MAIN_MANU_MESSAGE);

                    List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
                    List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();
                    List<InlineKeyboardButton> rowButtons3 = new ArrayList<>();

                    rowButtons1.add(newButton(WELCOME_NAME, WELCOME));

                    rowButtons2.add(newButtonWithUrl(SMICH_CHANNEL_NAME, SMICH_CHANNEL, SMICH_URL));
                    rowButtons2.add(newButtonWithUrl(BIMARIUM_CHANNEL_NAME, BIMARIUM_CHANNEL, BIMARIUM_URL));

                    rowButtons3.add(newButtonWithUrl(SMICH_WELCOME_CHANNEL_NAME, SMICH_WELCOME_CHANNEL, SMICH_WELCOME_URL));

                    InlineKeyboardMarkup markup = newMarkup3(rowButtons1, rowButtons2, rowButtons3);

                    message.setReplyMarkup(markup);

                    executeMessage1(message);
                }
                case MAIN_MANU -> executeMessage2(commandButton.buttonMainManu(chatId, messageId));
                case WELCOME -> executeMessage2(commandButton.buttonWelcome(chatId, messageId));
                case BANKS -> executeMessage2(commandButton.buttonBanks(chatId, messageId));
                case T_BANK -> executeMessage2(commandButton.buttonTBank(chatId, messageId));
                case ALFA_BANK -> executeMessage2(commandButton.buttonAlfaBank(chatId, messageId));
                case WORKS -> executeMessage2(commandButton.buttonWorks(chatId, messageId));
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
        return "TOKEN";
    }
}