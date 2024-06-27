import bot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {

        try {
            Bot bot = new Bot();
            TelegramBotsApi tba = new TelegramBotsApi(DefaultBotSession.class);
            tba.registerBot(bot);
            bot.init();

        } catch (TelegramApiException ex) {
            ex.getStackTrace();
        }

        System.out.println("Бот запущен!");
    }
}