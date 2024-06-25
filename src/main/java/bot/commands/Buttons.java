package bot.commands;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static bot.HelpMethods.*;
import static bot.finalVariables.ButtonNames.*;
import static bot.finalVariables.ButtonsCallBackData.*;
import static bot.finalVariables.Links.*;
import static bot.finalVariables.MessageTexts.*;

public class Buttons {

    public EditMessageText buttonBegin(long chatId, long messageId) {

        return editor(chatId, START_MESSAGE, messageId);
    }

    public EditMessageText buttonMainManu(long chatId, long messageId) {

        EditMessageText message = editor(chatId, MAIN_MANU_MESSAGE, messageId);

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons3 = new ArrayList<>();

        rowButtons1.add(newButton(WELCOME_NAME, WELCOME));

        rowButtons2.add(newButtonWithUrl(SMICH_CHANNEL_NAME, SMICH_CHANNEL, SMICH_URL));
        rowButtons2.add(newButtonWithUrl(BIMARIUM_CHANNEL_NAME, BIMARIUM_CHANNEL, BIMARIUM_URL));

        rowButtons3.add(newButtonWithUrl(SMICH_WELCOME_CHANNEL_NAME, SMICH_WELCOME_CHANNEL, SMICH_WELCOME_URL));

        InlineKeyboardMarkup markup = newMarkup3(rowButtons1, rowButtons2, rowButtons3);

        message.setReplyMarkup(markup);

        return message;
    }

    public EditMessageText buttonWelcome(long chatId, long messageId) {

        EditMessageText message = editor(chatId, WELCOME_MESSAGE, messageId);

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();

        rowButtons1.add(newButton(BANKS_NAME, BANKS));
        rowButtons1.add(newButton(WORKS_NAME, WORKS));

        rowButtons2.add(newButton(BACK_NAME, MAIN_MANU));

        InlineKeyboardMarkup markup = newMarkup2(rowButtons1, rowButtons2);

        message.setReplyMarkup(markup);

        return message;
    }

    public EditMessageText buttonBanks(long chatId, long messageId) {

        EditMessageText message = editor(chatId, BANKS_MESSAGE, messageId);

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons3 = new ArrayList<>();

        rowButtons1.add(newButton(T_BANK_NAME, T_BANK));
        rowButtons2.add(newButton(ALFA_BANK_NAME, ALFA_BANK));

        rowButtons3.add(newButton(MAIN_MANU_NAME, MAIN_MANU));
        rowButtons3.add(newButton(BACK_NAME, WELCOME));

        InlineKeyboardMarkup markup = newMarkup3(rowButtons1, rowButtons2, rowButtons3);

        message.setReplyMarkup(markup);

        return message;
    }

    public EditMessageText buttonTBank(long chatId, long messageId) {

        EditMessageText message = editor(chatId, T_BANK_MESSAGE, messageId);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons3 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons4 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons5 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons6 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons7 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons8 = new ArrayList<>();

        rowButtons1.add(newButtonWithUrl(T_BANK_WORK_NAME, URLS, T_BANK_WORK_URL));

        rowButtons2.add(newButtonWithUrl(T_BANK_BUSINESS_ACCOUNT_NAME, URLS, T_BANK_BUSINESS_ACCOUNT_URL));

        rowButtons3.add(newButtonWithUrl(T_BANK_BUSINESS_REGISTRATION_NAME, URLS, T_BANK_BUSINESS_REGISTRATION_URL));

        rowButtons4.add(newButtonWithUrl(T_BANK_BLACK_NAME, URLS, T_BANK_BLACK_URL));
        rowButtons4.add(newButtonWithUrl(T_BANK_PLATINUM_NAME, URLS, T_BANK_PLATINUM_URL));

        rowButtons5.add(newButtonWithUrl(T_BANK_MOBILE_NAME, URLS, T_BANK_MOBILE_URL));
        rowButtons5.add(newButtonWithUrl(T_BANK_INVESTMENT_NAME, URLS, T_BANK_INVESTMENT_URL));

        rowButtons6.add(newButtonWithUrl(T_BANK_CASCO_NAME, URLS, T_BANK_CASCO_URL));
        rowButtons6.add(newButtonWithUrl(T_BANK_OSAGO_NAME, URLS, T_BANK_OSAGO_URL));

        rowButtons7.add(newButtonWithUrl(CHOICE_PRODUCT_NAME, CHOICE_PRODUCT, T_BANK_CHOICE_PRODUCT_URL));

        rowButtons8.add(newButton(MAIN_MANU_NAME, MAIN_MANU));
        rowButtons8.add(newButton(BACK_NAME, BANKS));

        rowsButtons.add(rowButtons1);
        rowsButtons.add(rowButtons2);
        rowsButtons.add(rowButtons3);
        rowsButtons.add(rowButtons4);
        rowsButtons.add(rowButtons5);
        rowsButtons.add(rowButtons6);
        rowsButtons.add(rowButtons7);
        rowsButtons.add(rowButtons8);

        markup.setKeyboard(rowsButtons);
        message.setReplyMarkup(markup);

        return message;
    }

    public EditMessageText buttonAlfaBank(long chatId, long messageId) {

        EditMessageText message = editor(chatId, ALFA_BANK_MESSAGE, messageId);

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons3 = new ArrayList<>();

        rowButtons2.add(newButtonWithUrl(CHOICE_PRODUCT_NAME, CHOICE_PRODUCT, ALFA_BANK_CHOICE_PRODUCT_URL));

        rowButtons3.add(newButton(MAIN_MANU_NAME, MAIN_MANU));
        rowButtons3.add(newButton(BACK_NAME, BANKS));

        InlineKeyboardMarkup markup = newMarkup3(rowButtons1, rowButtons2, rowButtons3);

        message.setReplyMarkup(markup);

        return message;
    }

    public EditMessageText buttonWorks(long chatId, long messageId) {

        EditMessageText message = editor(chatId, WORKS_MESSAGE, messageId);

        List<InlineKeyboardButton> rowButtons1 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons2 = new ArrayList<>();
        List<InlineKeyboardButton> rowButtons3 = new ArrayList<>();

        rowButtons1.add(newButton(SBER_MARKET_NAME, SBER_MARKET));
        rowButtons2.add(newButton(YANDEX_SMENA_NAME, YANDEX_SMENA));

        rowButtons3.add(newButton(MAIN_MANU_NAME, MAIN_MANU));
        rowButtons3.add(newButton(BACK_NAME, WELCOME));

        InlineKeyboardMarkup markup = newMarkup3(rowButtons1, rowButtons2, rowButtons3);

        message.setReplyMarkup(markup);

        return message;
    }
}
