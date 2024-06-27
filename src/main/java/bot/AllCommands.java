package bot;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface AllCommands {
    List<BotCommand> LIST_OF_COMMAND = List.of(
            new BotCommand("/start", "запуск бота"),
            new BotCommand("/support", "тех. поддержка"),
            new BotCommand("/chatId", "ID чата"),
            new BotCommand("/usersId", "пользователи")
    );
}
